package org.editice.saber.facade.test.code.lc;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 可考虑按数字进行
 *
 *
 * @author tinglang
 * @date 2021/12/29.
 */
public class Test1 implements TestBase {

//    字符          数值
//    I             1
//    V             5
//    X             10
//    L             50
//    C             100
//    D             500
//    M             1000

    public String intToRoman(int num) {
        StringBuilder res= new StringBuilder();
        int[] arr = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for(int i=0;i<arr.length;i++){
            int tmp = arr[i];
            String symbol = symbols[i];
            while(num>=tmp){
                res.append(symbol);
                num-=tmp;
            }
        }
        return res.toString();
    }


    public int hammingDistance(int x, int y) {
        int z = x&y;

        int res = 0;
        while(z>0){
            int t = z%2;
            if(t==1){
                res++;
            }
            z=z/2;
        }
        return res;
    }


    @Test
    @Override
    public void test() {
        System.out.println('Z'-'A');
        assertEquals(2, hammingDistance(1,4));
    }

}