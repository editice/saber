package biz.sdk;

import framework.BizInstanceId;
import framework.IBizInstance;
import lombok.Data;

/**
 * @author tinglang
 * @date 2020/3/18.
 */
@Data
public class CreateOrderReq implements IBizInstance {

    private long buyerId;

    private String bizCode;

    private Long outOrderId;

    @Override
    public BizInstanceId<Long> getBizInstanceId() {
        return BizInstanceId.of(bizCode, outOrderId);
    }
}
