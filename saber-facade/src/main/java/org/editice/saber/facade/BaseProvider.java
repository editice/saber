package org.editice.saber.facade;

/**
 * @author tinglang
 * @date 2024/3/3.
 */
public class BaseProvider<IN,OUT> {

    protected final <IN,OUT> SpiNode mutex(Object... namespacesOrNodes) {
        return createSpiNode("mutex", applyArguments(namespacesOrNodes));
    }

    private SpiNode createSpiNode(String mutex, Object o) {
        return null;
    }

    private Object applyArguments(Object[] namespacesOrNodes) {
        return null;
    }
}
