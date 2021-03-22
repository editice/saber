package biz.application;

import biz.sdk.CreateOrderReq;
import biz.sdk.CreateTradeActionExt;
import framework.Action;
import framework.ActionSessionScope;

import java.util.List;
import java.util.Map;

import static framework.ExtensionInvoker.execAll;
import static framework.ExtensionInvoker.execFirst;

/**
 * 提交订单的业务活动测试入口
 *
 * @author tinglang
 * @date 2020/3/18.
 */
public class CreateOrderAction1 implements Action {

    public void doCreate(String bizCode, long buyerId, long outOrderId) throws Exception {
        //构建请求入参
        CreateOrderReq req = build(bizCode, buyerId, outOrderId);

        new ActionSessionScope<Void, Exception>(getCode()) {
            @Override
            protected Void _exec() {
                System.out.println(String.format("-----------> 业务身份：{%s} <---------- ", bizCode));

                //1.判断是否是测试订单，打印仲裁结果
                Boolean isTestOrder = execFirst(req,
                        CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER,
                        ext -> ((CreateTradeActionExt) ext).isTestOrder(req.getBuyerId()));
                System.out.println("执行结果（判断是否测试单）： " + isTestOrder);
                System.out.println();

                //2.获取订单打标结果，打印merge结果
                List<Map<String, String>> allAttr = execAll(req,
                        CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE,
                        ext -> ((CreateTradeActionExt) ext).enrichAttributes(req.getBuyerId()));
                System.out.println("执行结果（订单打标）：" + allAttr);
                System.out.println();

                System.out.println();
                return null;
            }
        }.invoke();
    }

    private CreateOrderReq build(String bizCode, long buyerId, long outOrderId) {
        CreateOrderReq req = new CreateOrderReq();
        req.setBuyerId(buyerId);
        req.setBizCode(bizCode);
        req.setOutOrderId(outOrderId);
        return req;
    }


    @Override
    public String getCode() {
        return "STORE_PAY_1_CREATE_ORDER_1";
    }
}
