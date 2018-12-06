package org.editice.saber.facade.conf;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tinglang
 * @date 2018/12/6.
 */
@Component
public class SaberConf {

    @Value("${appEnv}")
    @Getter
    private String appEnv;

    @Value("${appDesc}")
    @Getter
    private String appDesc;

}
