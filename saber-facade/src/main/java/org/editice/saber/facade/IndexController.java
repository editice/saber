package org.editice.saber.facade;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;

/**
 * @author tinglang
 * @date 17/7/19.
 */
@RestController
public class IndexController {

    @RequestMapping("/")
    public String print(){
        return "HELLO WORLD! YES!";
    }

}
