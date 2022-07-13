package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tinglang
 * @date 2022/7/7.
 */
public class Solution implements TestBase {

    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");
        int len = pattern.length();
        if(arr.length!=len){
            return false;
        }

        Map<Object, Integer> map = new HashMap<>();
        for(int i=0;i<len;i++) {
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(arr[i], i))){
                return false;
            }
        }

        return true;
    }

    @Test
    @Override
    public void test() {
        System.out.println(wordPattern("abba","dog dog dog dog"));
    }
}
