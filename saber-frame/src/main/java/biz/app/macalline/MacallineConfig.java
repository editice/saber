package biz.app.macalline;

import biz.sdk.CreateTradeActionExt;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import framework.BizConfig;
import framework.BizConfigSpec;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
public class MacallineConfig implements BizConfig {

    public String bizCode = "macalline";

    @Override
    public String getBizCode() {
        return bizCode;
    }

    @Override
    public BizConfigSpec getBizConfigSpec() {
        BizConfigSpec bizConfigSpec = new BizConfigSpec();

        bizConfigSpec.addSupportedProductCode("intraPurchase");
        bizConfigSpec.addSupportedProductCode("jumiaodai");


        bizConfigSpec.addProductPriorityConfig("intraPurchase", CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER, 2);
        bizConfigSpec.addProductPriorityConfig("jumiaodai", CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER, 3);

        bizConfigSpec.addProductPriorityConfig("intraPurchase", CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE, 2);
        bizConfigSpec.addProductPriorityConfig("jumiaodai", CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE, 1);

        return bizConfigSpec;
    }

}
