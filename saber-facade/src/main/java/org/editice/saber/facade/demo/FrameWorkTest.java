package org.editice.saber.facade.demo;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
public class FrameWorkTest {

    @SuppressWarnings("ALL")
    public static Result<Integer> test() throws Throwable {
        return new BizSessionScope<Result<Integer>, Throwable>() {
            @Override
            protected Result<Integer> _execute() throws Throwable, Throwable {
                Identifiable identifiable = new Identifiable();
                identifiable.setBizId("bili.ticket");//实际业务代码，这部分是计算出来的

                //计算订单确认收货超时时间
                int timeOut = Ext.invokeFirst(identifiable, ConfirmOrderExt.M,
                        ext -> ((ConfirmOrderExt) ext).calTimeOut());
                return Result.of(timeOut);
            }
        }.invoke();

    }
}
