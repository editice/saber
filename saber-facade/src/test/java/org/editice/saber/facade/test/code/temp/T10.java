package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author tinglang
 * @date 2022/7/13.
 */
public class T10 implements TestBase {

    public boolean canAttendMeetings(int[][] intervals) {

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int preTime = 0;
        for (int[] interval : intervals) {
            if (interval[0] < preTime) {
                return false;
            }

            preTime = interval[1];
        }

        return true;

    }

    @Test
    @Override
    public void test() {

    }
}
