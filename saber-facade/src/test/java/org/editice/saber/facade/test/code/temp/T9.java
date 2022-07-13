package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author tinglang
 * @date 2022/7/12.
 */
public class T9 implements TestBase {

    public String addBinary(String a, String b) {
        int c = 0;
        StringBuilder sb = new StringBuilder();
        int i=a.length()-1;
        int j=b.length()-1;
        while(i>=0 || j>=0){
            int x = 0;
            int y = 0;
            if(i>=0){
                x = a.charAt(i)-'0';
                i--;
            }

            if(j>=0){
                y = b.charAt(j)-'0';
                j--;
            }

            int sum = x+y+c;
            if(sum>1){
                c = 1;
                sb.append(0);
            }else{
                c=0;
                sb.append(sum);
            }

        }
        if(c==1){
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    @Test
    @Override
    public void test() {
        assertEquals(addBinary("1010","1011"),"10101");
    }
}
