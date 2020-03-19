package framework;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author tinglang
 * @date 2020/3/18.
 */
@Data
public class BizInstanceId<ID> implements Serializable {

    public String bizCode;

    private String actionCode;

    private ID uniqueId;

    public static <T> BizInstanceId<T> of(String bizCode, T uniqueId) {
        return new BizInstanceId<>(bizCode, uniqueId);
    }

    protected BizInstanceId(String bizCode, ID uniqueId) {
        this.bizCode = bizCode;
        this.uniqueId = uniqueId;
    }
}

