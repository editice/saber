package biz.product.intraPurchase;

import biz.sdk.CreateTradeActionExt;
import com.google.common.collect.Maps;
import framework.Extension;

import java.util.Map;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Extension(productCode = "intraPurchase")
public class IntraPurchaseCreateTradeExt implements CreateTradeActionExt {
    @Override
    public boolean isTestOrder(long buyerId) {
        //因为买家ID在100000以内的都是内部人员，对内部采购来说这部分都归类为测试订单
        return buyerId <= 100000;
    }

    @Override
    public Map<String, String> enrichAttributes(long buyerId) {
        Map<String, String> map = Maps.newHashMap();
        map.put("IntraPurchase", "1");
        return map;
    }
}
