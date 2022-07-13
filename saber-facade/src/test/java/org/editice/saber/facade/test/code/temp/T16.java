package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 最长回文子串
 *
 * @author tinglang
 * @date 2022/1/13.
 */
public class T16 implements TestBase {

    @Test
    @Override
    public void test() {
        assertEquals("bab",longestPalindrome("babad"));
        assertEquals("bb",longestPalindrome("cbbd"));
        assertEquals("a",longestPalindrome("a"));
    }

    public String longestPalindrome(String s) {
        if(s.length() == 1){
            return s;
        }
        return "";

    }

    public static void main(String[] args) {
        Boolean t = null;
        System.out.println(t?"true":"false");
    }


}
