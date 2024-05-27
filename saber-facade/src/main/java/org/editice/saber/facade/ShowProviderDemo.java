package org.editice.saber.facade;

/**
 * @author tinglang
 * @date 2024/3/3.
 */
public class ShowProviderDemo<IN,OUT> extends BaseProvider<IN,OUT>{

    protected SpiNode spiTree() {
        return mutex(
                NamespaceConstants.eticketNs,//票务
                NamespaceConstants.secKillNs,//秒杀
                NamespaceConstants.overseaNs, //海外
                NamespaceConstants.acgNs);//acg
    }


}
