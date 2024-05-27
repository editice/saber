package org.editice.saber.facade.demo;

import java.util.function.Function;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
public class Ext {

    /**
     * ExtensionContainer.call... 简写形式
     *
     * @param <EXT>
     * @param <OUT>
     * @param input
     * @param name
     * @param func
     * @return
     */
    public static <EXT, OUT> OUT invokeAll(Identifiable input, String name, Function<EXT, OUT> func) {
        return ExtensionContainer.instance().invoke(input, name, func);
    }

    public static <EXT, OUT> OUT invokeFirst(Identifiable input, String name, Function<EXT, OUT> func) {
        return ExtensionContainer.instance().invoke(input, name, func);
    }

}
