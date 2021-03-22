package framework;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tinglang
 * @date 2020/3/18.
 */
public class ExtensionInvoker {

    public static <Extension extends IExt, OUT> OUT execFirst(IBizInstance iBizInstance,
                                                              String extensionCode,
                                                              ExtensionCallBack<Extension, OUT> extensionCallBack) {
        return extensionCallBack.apply((Extension) loadExtensions(iBizInstance, extensionCode).get(0));

    }

    public static <Extension extends IExt, OUT> List<OUT> execAll(IBizInstance iBizInstance,
                                                                  String extensionCode,
                                                                  ExtensionCallBack<Extension, OUT> extensionCallBack) {
        List<OUT> results = new ArrayList<>();
        for (Object iExt : loadExtensions(iBizInstance, extensionCode)) {
            results.add(extensionCallBack.apply((Extension) iExt));
        }
        return results;

    }

    private static List<IExt> loadExtensions(IBizInstance iBizInstance, String extensionCode) {
        return ExtensionManager.getInstance().findExtension( extensionCode, iBizInstance.getBizInstanceId().getBizCode());
    }

}
