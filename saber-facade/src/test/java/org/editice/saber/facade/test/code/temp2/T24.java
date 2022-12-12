package org.editice.saber.facade.test.code.temp2;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tinglang
 * @date 2022/9/8.
 */
public class T24 implements TestBase {

    public boolean canPermutePalindrome(String s) {
        int[] arr = new int[26];
        for(char x: s.toCharArray()){
            arr[x-'a']++;
        }

        int jishuCnt = 0;
        for(int y:arr){
            jishuCnt += (y%2==0?0:1);
            if(jishuCnt>1){
                return false;
            }
        }

        return true;
    }

    @Test
    @Override
    public void test() {
        Assert.assertEquals(true,canPermutePalindrome("aab"));
    }
}
