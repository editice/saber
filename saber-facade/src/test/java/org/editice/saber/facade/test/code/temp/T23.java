package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tinglang
 * @date 2022/8/3.
 */
public class T23  implements TestBase {

    public String reverseStr(String s, int k) {
        int len = s.length();
        int reverseCnt = len / (2 * k);
        int rel = len % (2 * k);
        char[] arr = s.toCharArray();
        int i = 0;
        for (; i < reverseCnt; i++) {
            reverse(arr, i * 2 * k, k);
        }

        if (rel != 0) {
            reverse(arr, i * 2 * k, Math.min(rel, k));
        }

        return String.copyValueOf(arr);
    }

    public void reverse(char[] x, int pre, int len){
        for (int i = pre; i < pre + len / 2; i++) {
            char tmp = x[i];
            x[i] = x[len + 2*pre - 1 - i];
            x[len + 2*pre - 1 - i] = tmp;
        }
    }


    @Test
    @Override
    public void test() {
        Assert.assertEquals("bacdfeg", reverseStr("abcdefg",2));
    }
}
