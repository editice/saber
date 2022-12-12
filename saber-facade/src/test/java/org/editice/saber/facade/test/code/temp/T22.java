package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tinglang
 * @date 2022/7/23.
 */
public class T22  implements TestBase {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] res = new int[2];
        for(int i=0;i<nums[i];i++){
            int val = nums[i];
            if(map.keySet().contains(val)){
                res[0] = map.get(val);
                res[1]= i;
                return res;
            }else{
                map.put(target-val, i);
            }
        }
        return res;
    }

    @Test
    @Override
    public void test() {
        int[] res = new int[]{0,3};
        int[] input = new int[]{0,4,3,0};
        Assert.assertArrayEquals(res, twoSum(input, 0));
    }
}
