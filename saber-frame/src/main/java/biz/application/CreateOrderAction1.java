package biz.application;

import biz.sdk.CreateOrderReq;
import biz.sdk.CreateTradeActionExt;
import framework.Action;
import framework.ActionSessionScope;
import framework.ExtensionInvoker;

import java.util.List;
import java.util.Map;

/**
 * 提交订单的业务活动测试入口
 *
 * @author tinglang
 * @date 2020/3/18.
 */
public class CreateOrderAction1 implements Action {

    public void doCreate(String bizCode, long buyerId, long outOrderId) throws Exception {
        //构建请求入参
        CreateOrderReq createOrderReq = new CreateOrderReq();
        createOrderReq.setBuyerId(buyerId);
        createOrderReq.setBizCode(bizCode);
        createOrderReq.setOutOrderId(outOrderId);

        new ActionSessionScope<Void, Exception>(getCode()) {
            @Override
            protected Void _exec() {
                System.out.println(String.format("-----------> 业务身份：{%s} <---------- ", bizCode));

                //1.判断是否是测试订单，打印仲裁结果
                Boolean isTestOrder = ExtensionInvoker.executeFirstMatchedExtension(createOrderReq,
                        CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_CHECK_TEST_ORDER,
                        ext -> ((CreateTradeActionExt) ext).isTestOrder(createOrderReq.getBuyerId()));
                System.out.println("执行结果（判断是否测试单）： " + isTestOrder);
                System.out.println();

                //2.获取订单打标结果，打印merge结果
                List<Map<String, String>> allAttr = ExtensionInvoker.executeAllMatchedExtension(createOrderReq,
                        CreateTradeActionExt.STORE_TRADE_CREATE_TRADE_ENRICH_ATTRIBUTE,
                        ext -> ((CreateTradeActionExt) ext).enrichAttributes(createOrderReq.getBuyerId()));
                System.out.println("执行结果（订单打标）：" + allAttr);
                System.out.println();

                System.out.println();
                return null;
            }
        }.invoke();
    }


    @Override
    public String getCode() {
        return "STORE_PAY_1_CREATE_ORDER_1";
    }
}
