package org.editice.saber.facade.demo;

import java.util.function.Function;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
public class ExtensionContainer {

    private static ExtensionContainer instance = null;

    private static Object locker = new Object();

    //
    public static ExtensionContainer instance() {
        if (instance != null) {
            return instance;
        }

        synchronized (locker) {
            if (instance != null) {
                return instance;
            } else {
                instance = new ExtensionContainer();
            }
        }

        return instance;
    }

    public <EXT, OUT> OUT invoke(Identifiable input, String name, Function<EXT, OUT> func) {
        return null;
    }
}
