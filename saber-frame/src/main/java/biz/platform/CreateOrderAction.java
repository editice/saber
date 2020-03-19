package biz.platform;

import biz.app.easyHome.EasyHomeConfig;
import biz.app.easyHome.EasyHomeCreateTradeActionExt;
import biz.app.macalline.MacallineConfig;
import biz.app.macalline.MacallineCreateTradeActionExt;
import biz.product.intraPurchase.IntraPurchaseCreateTradeExt;
import biz.product.jumiaodai.JumiaodaiCreateTradeExt;
import biz.sdk.CreateOrderReq;
import biz.sdk.CreateTradeActionExt;
import com.google.common.collect.Lists;
import framework.ExtensionInvoker;
import framework.ExtensionManager;

import java.util.List;
import java.util.Map;

/**
 * 提交订单的业务活动测试入口
 *
 * @author tinglang
 * @date 2020/3/18.
 */
public class CreateOrderAction {

    public void doCreate(String bizCode, long buyerId, long outOrderId) {
        //构建请求入参
        CreateOrderReq createOrderReq = new CreateOrderReq();
        createOrderReq.setBuyerId(buyerId);
        createOrderReq.setBizCode(bizCode);
        createOrderReq.setOutOrderId(outOrderId);

        System.out.println("-----------> 业务身份： <---------- " + bizCode);

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

    }

    public static void main(String[] args) throws Exception {
        //模拟框架启动
        ExtensionManager instance = ExtensionManager.getInstance();
        instance.scanConfigSpec(Lists.newArrayList(
                new EasyHomeConfig(),
                new MacallineConfig()
        ));

        instance.scanExtensions(Lists.newArrayList(
                EasyHomeCreateTradeActionExt.class,
                MacallineCreateTradeActionExt.class,
                IntraPurchaseCreateTradeExt.class,
                JumiaodaiCreateTradeExt.class,
                CreateOrderAction.class
        ));


        //模拟正常的业务流程
        CreateOrderAction createOrderAction = new CreateOrderAction();

        createOrderAction.doCreate("easyHome", 11L, 1L);
        createOrderAction.doCreate("macalline", 11L, 2L);
    }
}
