package org.editice.saber.facade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tinglang
 * @date 2018/12/6.
 */
@Controller
public class MainController {

    @RequestMapping("/hello")
    public String hello(){
        return "index";
    }
}
