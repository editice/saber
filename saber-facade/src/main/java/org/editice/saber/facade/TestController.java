package org.editice.saber.facade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tinglang
 * @date 17/7/19.
 */
@RestController
public class TestController {

    @RequestMapping("/")
    public String print(){
        return "hello world";
    }
}
