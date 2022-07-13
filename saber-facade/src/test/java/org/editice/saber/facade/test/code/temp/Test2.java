package org.editice.saber.facade.test.code.temp;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * @author tinglang
 * @date 2021/12/30.
 */
public class Test2 {

//    输入：digits = "23"
//    输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//    输入：digits = "2"
//    输出：["a","b","c"]

    @Test
    public void test() {
        System.out.println(letterCombinations("238"));
        System.out.println(letterCombinations("2"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }


        Map<String, String[]> map = new HashMap<>();
        map.put("2", new String[]{"a", "b", "c"});
        map.put("3", new String[]{"d", "e", "f"});
        map.put("4", new String[]{"g", "h", "i"});
        map.put("5", new String[]{"j", "k", "l"});
        map.put("6", new String[]{"m", "n", "o"});
        map.put("7", new String[]{"p", "q", "r", "s"});
        map.put("8", new String[]{"t", "u", "v"});
        map.put("9", new String[]{"w", "x", "y", "z"});

        List<String> records = Lists.newArrayList();
        for (int i = 0; i < digits.length(); i++) {
            String index = digits.substring(i, i + 1);
            String[] str = map.get(index);
            if (records.size() == 0) {
                records.addAll(Arrays.asList(str));
                continue;
            }

            records = gen(str, records);
        }

        return records;
    }

    public List<String> gen(String[] strs, List<String> originRecord) {
        List<String> res = new ArrayList<>();
        for (String s : originRecord) {
            for (String tmp : strs) {
                String tt = s + tmp;
                res.add(tt);
            }
        }

        return res;

    }


}
