package org.editice.saber.tool;

import java.lang.instrument.Instrumentation;

/**
 * @author tinglang
 * @date 2018/10/22.
 */
public class SaberAgentLauncher {

    public static void premain(String args, Instrumentation inst) {
        doCore(args, inst);
    }

    public static void agentmain(String args, Instrumentation inst) {
        doCore(args, inst);
    }

    private static volatile ClassLoader saberClassLoader;

    private static synchronized void resetClassLoader() {
        saberClassLoader = null;
    }


    private static synchronized void doCore(final String args, Instrumentation inst) {

        try {
            final int index = args.indexOf(";");
            final String agentJar = args.substring(0, index);
            final String agetArgs = args.substring(index);

            System.err.println("agentJar: " + agentJar);
            System.err.println("agentArgs: " + agetArgs);

            final ClassLoader classLoader = loadOrDefineClassLoader(agentJar);


            //use reflection to get java pid from args
            final Class<?> argsParamClass = classLoader.loadClass("org.editice.saber.tool.core.ArgsParam");
            final Object argsParamObj = argsParamClass.getMethod("toParam", String.class).invoke(null, agetArgs);
            final int javaPid = (Integer) argsParamClass.getMethod("getJavaPid", (Class<?>[]) null)
                    .invoke(argsParamObj, (Object[]) null);

            //get agent server from classloader, if not exists then build one
            final Class<?> saberServerClass = classLoader.loadClass("org.editice.saber.tool.core.SaberServer");
            final Object saberServerObj = saberServerClass.getMethod("getInstance", int.class, Instrumentation.class)
                    .invoke(null, javaPid, inst);

            System.err.println(saberServerObj.getClass().getName());
            final Boolean isBind = (Boolean) saberServerClass.getMethod("isBind").invoke(saberServerObj);

            System.err.println("isBind:" + isBind);

            if (!isBind) {
                try {
                    saberServerClass.getMethod("bind", argsParamClass).invoke(saberServerObj, argsParamObj);
                } catch (Throwable t) {
                    saberServerClass.getMethod("destroy").invoke(saberServerObj);
                    throw t;
                }
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    private static ClassLoader loadOrDefineClassLoader(String agentJar) throws Throwable {
        final ClassLoader classLoader;

        //if started, then use it
        if (null != saberClassLoader) {
            classLoader = saberClassLoader;
        } else {
            classLoader = new SaberClassLoader(agentJar);
        }

        return saberClassLoader = classLoader;
    }
}
