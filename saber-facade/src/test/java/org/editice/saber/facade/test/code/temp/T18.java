package org.editice.saber.facade.test.code.temp;

import java.util.TreeSet;

/**
 * @author tinglang
 * @date 2022/7/14.
 */
public class T18 {

    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int x:nums){
            set.add(x);
            if(set.size()>3){
                set.remove(set.first());
            }
        }

        return set.size()==3?set.first():set.last();
    }
}
