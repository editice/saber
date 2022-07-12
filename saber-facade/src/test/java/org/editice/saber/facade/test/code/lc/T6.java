package org.editice.saber.facade.test.code.lc;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author tinglang
 * @date 2022/7/11.
 */
public class T6 implements TestBase {
    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while(left<=right){
            int mid = (left+right)/2;
            long square = (long)mid*mid;
            if(square<num){
                left = mid+1;
            }else if(square>num){
                right = mid-1;
            }else{
                return true;
            }
        }

        return false;
    }

    @Test
    @Override
    public void test() {
        assertEquals(isPerfectSquare(15), false);
    }
}
