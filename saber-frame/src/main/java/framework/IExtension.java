package framework;

/**
 * @author tinglang
 * @date 2020/3/18.
 */
@SuppressWarnings("unchecked")
public interface IExtension extends IExtFacade {

    default IExt getExtensionByCode(ExtensionSpec extensionSpec) {
        return null;//预留解耦用
    }
}
