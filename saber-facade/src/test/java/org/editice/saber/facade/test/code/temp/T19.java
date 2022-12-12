package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author tinglang
 * @date 2022/7/18.
 */
public class T19 implements TestBase {

    public int findComplement(int num) {
        StringBuilder sb = new StringBuilder();
        for(char x:Integer.toBinaryString(num).toCharArray()){
            sb.append('1'-x);
        }

        char[] x = "123".toCharArray();
        System.out.println(String.copyValueOf(x));

        return Integer.parseInt(sb.toString(),2);
    }

    @Test
    @Override
    public void test() {
        assertEquals(2,findComplement(5));
    }
}
