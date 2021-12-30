package org.editice.saber.facade.test.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 可考虑按数字进行
 *
 *
 * @author tinglang
 * @date 2021/12/29.
 */
public class Test1 {

//    字符          数值
//    I             1
//    V             5
//    X             10
//    L             50
//    C             100
//    D             500
//    M             1000

    @Test
    public void test1() {
        assertEquals("LVIII", intToRoman(58));
        assertEquals("MCMXCIV", intToRoman(1994));
        assertEquals("MM", intToRoman(2000));
    }

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
//
//    public String intToRoman(int num) {
//        String res = "";
//        int t = num / 1000;
//        if (num / 1000 > 0) {
//            switch (t){
//                case 1:
//                    res+="M";
//                    break;
//                case 2:
//                    res+="MM";
//                    break;
//                case 3:
//                    res+="MMM";
//                    break;
//            }
//        }
//
//        int x = num % 1000 / 100;
//        if (x > 0) {
//            switch (x) {
//                case 1:
//                    res += "C";
//                    break;
//                case 2:
//                    res += "CC";
//                    break;
//                case 3:
//                    res += "CCC";
//                    break;
//                case 4:
//                    res += "CD";
//                    break;
//                case 5:
//                    res += "D";
//                    break;
//                case 6:
//                    res += "DC";
//                    break;
//                case 7:
//                    res += "DCC";
//                    break;
//                case 8:
//                    res += "DCCC";
//                    break;
//                case 9:
//                    res += "CM";
//                    break;
//            }
//        }
//
//        int Y = num % 100 / 10;
//        if (Y > 0) {
//            switch (Y) {
//                case 1:
//                    res += "X";
//                    break;
//                case 2:
//                    res += "XX";
//                    break;
//                case 3:
//                    res += "XXX";
//                    break;
//                case 4:
//                    res += "XL";
//                    break;
//                case 5:
//                    res += "L";
//                    break;
//                case 6:
//                    res += "LX";
//                    break;
//                case 7:
//                    res += "LXX";
//                    break;
//                case 8:
//                    res += "LXXX";
//                    break;
//                case 9:
//                    res += "XC";
//                    break;
//            }
//        }
//        int Z = num % 10;
//        if (Z > 0) {
//            switch (Z) {
//                case 1:
//                    res += "I";
//                    break;
//                case 2:
//                    res += "II";
//                    break;
//                case 3:
//                    res += "III";
//                    break;
//                case 4:
//                    res += "IV";
//                    break;
//                case 5:
//                    res += "V";
//                    break;
//                case 6:
//                    res += "VI";
//                    break;
//                case 7:
//                    res += "VII";
//                    break;
//                case 8:
//                    res += "VIII";
//                    break;
//                case 9:
//                    res += "IX";
//                    break;
//            }
//        }
//
//
//        return res;
//    }

}