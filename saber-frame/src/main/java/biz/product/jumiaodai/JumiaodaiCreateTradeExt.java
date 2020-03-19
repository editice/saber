package biz.product.jumiaodai;

import biz.sdk.CreateTradeActionExt;
import com.google.common.collect.Maps;
import framework.Extension;

import java.util.Map;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Extension(productCode = "jumiaodai")
public class JumiaodaiCreateTradeExt implements CreateTradeActionExt {
    @Override
    public boolean isTestOrder(long buyerId) {
        return false;
    }

    @Override
    public Map<String, String> enrichAttributes(long buyerId) {
        Map<String, String> map = Maps.newHashMap();
        map.put("jumiaodai", "1");
        return map;
    }
}
