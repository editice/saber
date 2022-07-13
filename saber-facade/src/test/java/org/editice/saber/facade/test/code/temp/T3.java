package org.editice.saber.facade.test.code.temp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tinglang
 * @date 2022/7/10.
 */
public class T3 {
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] arr = new int[1001];
        for(int x:nums1){
            arr[x]++;
        }

        Set<Integer> res = new HashSet<>();
        for(int y:nums2){
            if(arr[y]>0){
                res.add(y);
            }
        }

        int[] result = new int[res.size()];
        int i=0;
        for(int t:res){
            result[i++]=t;
        }

        return result;
    }
}
