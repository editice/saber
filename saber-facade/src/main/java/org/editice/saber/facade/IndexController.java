package org.editice.saber.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tinglang
 * @date 17/7/19.
 */
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String print(){
        return "HELLO WORLD! YES!";
    }

    @RequestMapping("/test")
    public String print1() throws InterruptedException {
        for(int i=0;i<100;i++){
            testLowRt(i);
            testLowRt1(i);
        }
        return "HELLO WORLD! YES!";
    }

    public void testLowRt(int max) throws InterruptedException {
        for(int i=0;i<max;i++){
            Thread.sleep(10);
            logger.warn(String.format("test low rt %s", i));
        }
    }

    public void testLowRt1(int max) throws InterruptedException {
        for(int i=0;i<max;i++){
            Thread.sleep(10);
            logger.warn("test low rt "+i);
        }
    }


}
