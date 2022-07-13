package org.editice.saber.facade.test.code.temp;

import java.util.Arrays;

/**
 * @author tinglang
 * @date 2021/12/31.
 */
public class T13 {

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int res = nums[0] + nums[1] + nums[2];
        if (res == target) {
            return res;
        }

        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {

            int s = i + 1;
            int t = len - 1;
            while (s < t) {
                int sum = nums[i] + nums[s] + nums[t];
                if (sum == target) {
                    return sum;
                }

                int tmpMin = Math.abs(target - sum);
                if (min > tmpMin) {
                    res = sum;
                    min = tmpMin;
                }

                if (sum > target) {
                    t--;
                } else {
                    s++;
                }
            }


        }

        return res;
    }


}
