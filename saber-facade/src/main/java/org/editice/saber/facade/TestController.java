package org.editice.saber.facade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author tinglang
 * @date 17/7/19.
 */
@RestController
public class TestController {

    @RequestMapping("/test1/")
    public ModelAndView print(){
        String url = "http://m.tb.cn/q1.X51ao";
        return new ModelAndView(new RedirectView(url));
    }

    @RequestMapping("/h5demo/")
    public String test2(){
        String url = "http://m.tb.cn/q1.X51ao";
        return "/h5demo";
    }
}
