package org.editice.saber.facade.test.code.temp2;

import org.junit.Test;

/**
 * @author tinglang
 * @date 2024/1/2.
 */
public class TestPerf {

    public void testLowRt(int max) throws InterruptedException {
        for(int i=0;i<max;i++){
            Thread.sleep(10);
            System.out.println("xx");
        }
    }

    @Test
    public void test() throws InterruptedException {
        for(int i=0;i<1000;i++){
            testLowRt(i);
        }
    }
}
