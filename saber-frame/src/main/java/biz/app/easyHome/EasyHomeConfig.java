package biz.app.easyHome;

import biz.sdk.CreateTradeActionExt;
import framework.BizConfig;
import framework.BizConfigSpec;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
public class EasyHomeConfig implements BizConfig {

    public String bizCode = "easyHome";

    @Override
    public String getBizCode() {
        return bizCode;
    }


    @Override
    public BizConfigSpec getBizConfigSpec() {
        BizConfigSpec bizConfigSpec = new BizConfigSpec();

        bizConfigSpec.addSupportedProductCode("intraPurchase");
        bizConfigSpec.addProductPriorityConfig("intraPurchase", CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER, 2);
        bizConfigSpec.addProductPriorityConfig("intraPurchase", CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE, 2);
        return bizConfigSpec;
    }
}
