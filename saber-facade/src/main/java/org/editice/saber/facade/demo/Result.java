package org.editice.saber.facade.demo;

import lombok.Data;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
@Data
public class Result<T> {

    T res;

    public static <T> Result of(T x){
        Result result = new Result();
        result.res = x;
        return result;
    }
}
