package framework;

import java.util.function.Function;

/**
 * @author tinglang
 * @date 2020/3/18.
 */
public interface ExtensionCallBack<IN extends IExt, OUT> extends Function<IN,OUT> {

    OUT apply(IN extension);
}
