package biz.app.easyHome;

import biz.sdk.CreateTradeActionExt;
import com.google.common.collect.Maps;
import framework.Extension;

import java.util.Map;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Extension(bizCode = "easyHome", supportActions = {"STORE_PAY_2_CREATE_ORDER_2","STORE_PAY_3_CREATE_ORDER_3"})
public class EasyHomeUniqueTradeActionExt implements CreateTradeActionExt {
    @Override
    public boolean isTestOrder(long buyerId) {
        return false;
    }

    @Override
    public Map<String, String> enrichAttributes(long buyerId) {
        Map<String, String> map = Maps.newHashMap();
        map.put("easyHome", "1");
        map.put("action", "2或3");
        return map;
    }
}
