package org.editice.saber.facade.demo;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
public interface ConfirmOrderExt {

    String M = "someAction#extCode_2";

    @Extension(code = M, author = "听浪", desc = "计算确认收货超时时间")
    default int calTimeOut() {
        return 1;
    }
}
