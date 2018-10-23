package org.editice.saber.tool.core;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.nio.channels.SelectionKey.OP_ACCEPT;

/**
 * @author tinglang
 * @date 2018/10/23.
 */
public class SaberServer {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static final byte EOT = 0x04;//终止符
    private static final int EOF = -1;


    private final int javaPid;
    private final AtomicBoolean serverBind = new AtomicBoolean(false);
    private final Thread jvmShutDownHooker = new Thread("saber-shutdown-hooker") {
        @Override
        public void run() {
            SaberServer.this._destroy();
        }
    };

    private final ExecutorService executorService = Executors.newCachedThreadPool(r -> {
        final Thread t = new Thread(r, "saber-command-execute-daemon");
        t.setDaemon(true);
        return t;
    });


    private ServerSocketChannel serverSocketChannel = null;
    private Selector selector = null;

    private static volatile SaberServer saberServer;

    private SaberServer(int javaPid, Instrumentation inst) {
        this.javaPid = javaPid;

        Runtime.getRuntime().addShutdownHook(jvmShutDownHooker);
    }

    private void _destroy() {
        Runtime.getRuntime().removeShutdownHook(jvmShutDownHooker);

    }

    public void destroy(){
        if (serverBind.get()) {
            unbind();
        }

        executorService.shutdown();
        _destroy();
    }

    public boolean isBind(){
        return serverBind.get();
    }

    public void bind(ArgsParam argsParam) throws IOException{
        if (!serverBind.compareAndSet(false, true)) {
            throw new IllegalStateException("already bind");
        }

        try {

            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().setSoTimeout(argsParam.getConnectTimeout());
            serverSocketChannel.socket().setReuseAddress(true);
            serverSocketChannel.register(selector, OP_ACCEPT);

            // 服务器挂载端口
            serverSocketChannel.socket().bind(getInetSocketAddress(argsParam.getTargetIp(), argsParam.getTargetPort()), 24);

            //激活后端线程
            activeSelectorDaemon(selector, argsParam);

        } catch (IOException e) {
            unbind();
            throw e;
        }
    }

    private void activeSelectorDaemon(Selector selector, ArgsParam argsParam) {
        final ByteBuffer byteBuffer = ByteBuffer.allocate(4 * 1024);//默认4K

        final Thread gaServerSelectorDaemon = new Thread("saber-selector-daemon") {
            @Override
            public void run() {
                System.err.println("trace: 1");

                while (!isInterrupted()
                        && isBind()) {

                    try {

                        while (selector.isOpen()
                                && selector.select() > 0) {
                            final Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                            while (it.hasNext()) {
                                final SelectionKey key = it.next();
                                it.remove();

                                // do ssc accept
                                if (key.isValid() && key.isAcceptable()) {
                                    doAccept(key, selector, argsParam);
                                }

                                // do sc read
                                if (key.isValid() && key.isReadable()) {
                                    doRead(byteBuffer, key);
                                }

                            }
                        }

                    } catch (Throwable e) {
                        e.printStackTrace();
                    }


                }

            }
        };
        gaServerSelectorDaemon.setDaemon(true);
        gaServerSelectorDaemon.start();
    }

    private void doAccept(SelectionKey key, Selector selector, ArgsParam param) throws IOException{
        final ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        final SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.socket().setSoTimeout(param.getConnectTimeout());
        socketChannel.socket().setTcpNoDelay(true);

        socketChannel.register(selector, SelectionKey.OP_READ);

        //输出logo
        String logoMsg = "HELLO EVERY BODY";
        ByteBuffer byteBuffer = ByteBuffer.wrap(logoMsg.getBytes(DEFAULT_CHARSET));
        while(byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }

        //输出终止符
        ByteBuffer byteBufferEnd = ByteBuffer.wrap(new byte[]{EOT});
        while(byteBufferEnd.hasRemaining()){
            socketChannel.write(byteBufferEnd);
        }
    }

    private void doRead(ByteBuffer byteBuffer, SelectionKey key){
        final SocketChannel socketChannel = (SocketChannel) key.channel();
        try {

            // 若读到EOF，则说明SocketChannel已经关闭
            if (EOF == socketChannel.read(byteBuffer)) {
                // closeSocketChannel(key, socketChannel);
                return;
            }

            // decode for line
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        // 会话只有未锁定的时候才能响应命令
                        try {

                            System.err.println("test info");
                            // 命令执行
//                            commandHandler.executeCommand(line, session);

                            // 命令结束之后需要传输EOT告诉client命令传输已经完结，可以展示提示符
                            socketChannel.write(ByteBuffer.wrap(new byte[]{EOT}));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                break;
            }//while for line decode

            byteBuffer.clear();
        }

        // 处理
        catch (IOException e) {
            closeSocketChannel(key, socketChannel);
        }
    }

    private void closeSocketChannel(SelectionKey key, SocketChannel socketChannel) {
        try {
            if (socketChannel != null) {
                socketChannel.close();
            }
        } catch (IOException ignored) {
        }
        key.cancel();
    }

    /*
     * 获取绑定网络地址信息<br/>
     * 这里做个小修正,如果targetIp为127.0.0.1(本地环回口)，则需要绑定所有网卡
     * 否则外部无法访问，只能通过127.0.0.1来进行了
     */
    private InetSocketAddress getInetSocketAddress(String targetIp, int targetPort) {
        if ("127.0.0.1".equals(targetIp)) {
            return new InetSocketAddress(targetPort);
        } else {
            return new InetSocketAddress(targetIp, targetPort);
        }
    }

    public void unbind() {
        try {
            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
        } catch (IOException ignored) {
        }

        try {
            if (selector != null) {
                selector.close();
            }
        } catch (IOException ignored) {
        }

        if (!serverBind.compareAndSet(true, false)) {
            throw new IllegalStateException("already unbind");
        }
    }

    public static SaberServer getInstance(final int javaPid, final Instrumentation inst) {
        if (null == saberServer) {
            synchronized (SaberServer.class) {
                if (null == saberServer) {
                    saberServer = new SaberServer(javaPid, inst);
                }
            }
        }

        return saberServer;
    }
}
