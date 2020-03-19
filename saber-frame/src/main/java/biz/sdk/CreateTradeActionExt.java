package biz.sdk;

import framework.ExtensionIdentity;
import framework.IExtension;

import java.util.Map;

/**
 * 商业能力：门店开单
 * 业务活动：创建订单
 * 扩展点命名方式：{商业能力}_{业务活动}_{扩展点}
 *
 * @author tinglang
 * @date 2020/3/18.
 */
public interface CreateTradeActionExt extends IExtension {

    String STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER = "STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER";

    String STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE = "STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE";

    @ExtensionIdentity(code = STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER)
    boolean isTestOrder(long buyerId);

    @ExtensionIdentity(code = STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE)
    Map<String, String> enrichAttributes(long buyerId);
}
