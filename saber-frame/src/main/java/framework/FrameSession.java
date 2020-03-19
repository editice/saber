package framework;

/**
 * 请务必注意，同一次调用不允许出现多个PaySession实例 ！！！！
 *
 * @author tinglang
 * @date 2018/8/8.
 */
public class FrameSession<Param extends SessionParam> {

    private static ThreadLocal<FrameSession> local = new ThreadLocal();


    private Param param;

    public Param getParam(){
        return param;
    }

    public <M extends Param> void setParam(M param){
        this.param = param;
    }


    public static FrameSession currentSession() {
        return local.get();
    }

    public static void destroySession() {
        FrameSession session = local.get();
        if (null != session) {
            session.destroy();
        }
        local.set(null);
    }

    public void create() {
        if (null != local.get()) {
            throw new RuntimeException("local contains session");
        }

        local.set(this);
    }

    public void destroy(){
        local.set(null);
    }
}
