package biz;

import biz.app.easyHome.EasyHomeConfig;
import biz.app.easyHome.EasyHomeNormalTradeActionExt;
import biz.app.easyHome.EasyHomeUniqueTradeActionExt;
import biz.app.macalline.MacallineConfig;
import biz.app.macalline.MacallineCreateTradeActionExt;
import biz.application.CreateOrderAction1;
import biz.application.CreateOrderAction2;
import biz.application.CreateOrderAction3;
import biz.product.intraPurchase.IntraPurchaseCreateTradeExt;
import biz.product.jumiaodai.JumiaodaiCreateTradeExt;
import com.google.common.collect.Lists;
import framework.ExtensionManager;

/**
 * 示例背景：
 * <p>
 * 有三个商业能力：store_pay_1\store_pay_2\store_pay_3
 * 有三个业务活动：createOrderAction1\createOrderAction2\createOrderAction3 （实现逻辑都是一样的，只有actionCode不同）
 * 创建订单的业务活动有两个扩展点：
 * <p>
 * 1. 判断是否是测试订单；
 * 2. 订单打标；
 * <p>
 * 业务身份有两个：居然 & 红星；
 * 产品有两个：内部采购  & 居秒贷；
 * <p>
 * 其中：
 * 1. 居然仅订阅了内部采购产品；
 * 2. 红星订阅了内部采购和居秒贷产品；
 * 3. 居然和红星两个业务对于两个扩展点的配置，业务和产品的叠加优先级不同；
 * 4. 居然在createOrderAction2 和 createOrderAction3 两个业务活动中对于订单打标的逻辑相同，
 * 但是和在createOrderAction1中的逻辑不同，根据不同的action分别打印不同的订单标；
 *
 * @author tinglang
 * @date 2020/3/19.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //模拟框架启动
        intitialFrameWork();

        //模拟不同业务身份和产品对于扩展点的叠加关系处理的case
        CreateOrderAction1 createOrderAction1 = new CreateOrderAction1();
        createOrderAction1.doCreate("easyHome", 11L, 1L);
        createOrderAction1.doCreate("macalline", 11L, 2L);

        //模拟不同业务活动下，扩展点实现相同和实现不同的case
        createOrderAction1.doCreate("easyHome", 11L, 1L);
        new CreateOrderAction2().doCreate("easyHome", 11L, 1L);
        new CreateOrderAction3().doCreate("easyHome", 11L, 1L);
    }


    private static void intitialFrameWork() throws Exception {
        ExtensionManager instance = ExtensionManager.getInstance();

        //模拟包扫描过程
        instance.scanConfigSpec(Lists.newArrayList(
                new EasyHomeConfig(),
                new MacallineConfig()
        ));

        //模拟包扫描过程
        instance.scanExtensions(Lists.newArrayList(
                EasyHomeNormalTradeActionExt.class,
                EasyHomeUniqueTradeActionExt.class,
                MacallineCreateTradeActionExt.class,
                IntraPurchaseCreateTradeExt.class,
                JumiaodaiCreateTradeExt.class,
                CreateOrderAction1.class
        ));

    }
}
