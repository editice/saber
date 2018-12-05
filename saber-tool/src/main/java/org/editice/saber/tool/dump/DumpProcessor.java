package org.editice.saber.tool.dump;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tinglang
 * @date 2018/12/5.
 */
public class DumpProcessor {

    public static void main(String[] args) throws Exception{

        relaunchSaJdi(args);
        System.exit(new DumpProcessor().exec(args));
    }

    public int exec(String[] args){
        ClassLoader classLoader = DumpProcessor.class.getClassLoader();
        return 0;
    }

    private static void relaunchSaJdi(String[] args) throws Exception {
        try {
            DumpProcessor.class.getClassLoader().loadClass("sun.jvm.hotspot.tools.jcore.ClassDump");
        } catch (ClassNotFoundException e) {
            System.out.println("NOT FIND CLASS, try to reload from java.home.");

            String javaHomePath = System.getProperty("java.home") == null ?
                    System.getenv("JAVA_HOME") : System.getProperty("java.home");

            if (javaHomePath == null) {
                System.out.println("NOT FIND JAVA_HOME! fail!");
                System.exit(-1);
            }

            File file = new File(javaHomePath + "/lib/sa-jdi.jar");
            if (!file.exists()) {
                file = new File(javaHomePath + "/../lib/sa-jdi.jar");
                if (!file.exists()) {
                    System.out.println("NOT FIND sa-jdi.jar from path: " + javaHomePath);
                    System.exit(-1);
                }
            }

            //use new classloader to prevent invade normal system
            List<URL> urls = new ArrayList<>(Arrays.asList(
                    ((URLClassLoader) DumpProcessor.class.getClassLoader()).getURLs()));

            urls.add(file.toURI().toURL());

            URLClassLoader urlClassLoader = new URLClassLoader(urls.toArray(new URL[0]),
                    ClassLoader.getSystemClassLoader().getParent());

            Class<?> startClass = urlClassLoader.loadClass(DumpProcessor.class.getName());
            final Method mainMethod = startClass.getMethod("main", String.class);
            if (!mainMethod.isAccessible()) {
                mainMethod.setAccessible(true);
            }

            mainMethod.invoke(null, new Object[]{args});
        }
    }
}
