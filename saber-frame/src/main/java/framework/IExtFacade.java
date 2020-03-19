package framework;

/**
 * @author tinglang
 * @date 2020/3/18.
 */
public interface IExtFacade extends IExt {

    <Extension extends IExt> Extension getExtensionByCode(ExtensionSpec extensionSpec);
}
