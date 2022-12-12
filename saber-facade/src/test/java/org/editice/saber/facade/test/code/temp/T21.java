package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author tinglang
 * @date 2022/7/20.
 */
public class T21 implements TestBase {

    public boolean isValid(String arr) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('(',1);
        map.put(')',-1);
        map.put('[',2);
        map.put(']',-2);
        map.put('{',3);
        map.put('}',-3);
        Deque<Character> queue = new LinkedList<>();
        for(char x:arr.toCharArray()){
            int val = map.get(x);
            if(val>0){
                queue.add(x);
            }else{
                if(map.get(queue.peekLast())+val==0){
                    queue.pop();
                }else{
                    return false;
                }
            }

        }
        return queue.isEmpty();

    }

    @Test
    @Override
    public void test() {
        Assert.assertEquals(false, isValid("([)]"));
    }
}
