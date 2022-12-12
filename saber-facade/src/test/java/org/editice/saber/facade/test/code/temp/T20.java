package org.editice.saber.facade.test.code.temp;

import org.editice.saber.facade.test.code.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tinglang
 * @date 2022/7/20.
 */
public class T20 implements TestBase {

    public String[] findWords(String[] words) {
        return null;
    }

    @Test
    @Override
    public void test() {
        String[] input = new String[]{"Hello","Alaska","Dad","Peace"};
        String[] res = new String[]{"Alaska","Dad"};
        Assert.assertArrayEquals(res, findWords(input));
    }
}
