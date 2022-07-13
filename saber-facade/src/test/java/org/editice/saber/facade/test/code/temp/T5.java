package org.editice.saber.facade.test.code.temp;

/**
 * @author tinglang
 * @date 2022/7/10.
 */
public class T5 {
    public int firstBadVersion(int n) {
        int min = 1;
        int max = n;
        int k = (min + max) / 2;
        while (k > 1) {
            if (isBadVersion(k)) {
                if (!isBadVersion(k - 1)) {
                    return k;
                }

                max = k;
            } else {
                min = k;
            }
            k = (min + max) / 2;
        }

        return n;
    }

    private boolean isBadVersion(int k) {
        return k >= 59;
    }

}
