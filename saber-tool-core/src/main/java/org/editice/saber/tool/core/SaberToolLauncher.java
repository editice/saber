package org.editice.saber.tool.core;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.util.List;

/**
 * @author tinglang
 * @date 2018/10/22.
 */
public class SaberToolLauncher {

    public SaberToolLauncher(String[] args) throws Exception {
        ArgsParam param = parse(args);
        attachAgent(param);
    }

    public static void main(String[] args) {
        try {
            new SaberToolLauncher(args);
        } catch (Throwable t) {
            t.printStackTrace();
            System.err.println("start failed! e=" + t.getMessage());
            System.exit(-1);
        }
    }

    private ArgsParam parse(String[] args) {
        final OptionParser parser = new OptionParser();
        parser.accepts("pid").withRequiredArg().ofType(int.class).required();
        parser.accepts("target").withOptionalArg().ofType(String.class);
        parser.accepts("core").withOptionalArg().ofType(String.class);
        parser.accepts("agent").withOptionalArg().ofType(String.class);

        final OptionSet optionSet = parser.parse(args);
        final ArgsParam argsParam = new ArgsParam();

        if (optionSet.has("target")) {
            final String[] strSplit = ((String) optionSet.valueOf("target")).split(":");
            argsParam.setTargetIp(strSplit[0]);
            argsParam.setTargetPort(Integer.valueOf(strSplit[1]));
        }

        argsParam.setJavaPid((Integer) optionSet.valueOf("pid"));
        argsParam.setAgent((String) optionSet.valueOf("agent"));
        argsParam.setCore((String) optionSet.valueOf("core"));
        return argsParam;
    }

    private void attachAgent(ArgsParam argsParam) throws Exception {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();

        System.out.println(loader.getClass().getName());
        System.out.println("pid=" + argsParam.getJavaPid());

        final Class<?> vmdClass = loader.loadClass("com.sun.tools.attach.VirtualMachineDescriptor");
        final Class<?> vmClass = loader.loadClass("com.sun.tools.attach.VirtualMachine");

        Object attachVmDescriptor = null;
        for (Object obj : (List<?>) vmClass.getMethod("list", (Class<?>[]) null).invoke(null, (Object[]) null)) {
            Object vmDescriptorId = vmdClass.getMethod("id", (Class<?>[]) null).invoke(obj, (Object[]) null);
            if (vmDescriptorId.equals(Integer.toString(argsParam.getJavaPid()))) {
                attachVmDescriptor = obj;
            }
        }

        System.err.println("attachVmDescriptor is null? "+ (attachVmDescriptor==null));

        Object attachVm = null;
        try {
            if (null == attachVmDescriptor) {
                attachVm = vmClass.getMethod("attach", String.class).invoke(null, "" + argsParam.getJavaPid());
            } else {
                attachVm = vmClass.getMethod("attach", vmdClass).invoke(null, attachVmDescriptor);
            }

            System.err.println("attachVm: "+ attachVm.getClass().getName());

            vmClass.getMethod("loadAgent", String.class, String.class).invoke(attachVm, argsParam.getAgent(), argsParam.getCore() + ";" + argsParam.toString());
        } finally {
            if (null != attachVm) {
                vmClass.getMethod("detach", (Class<?>[]) null).invoke(attachVm, (Object[]) null);

                System.err.println("detach successful!");
            }
        }
    }
}
