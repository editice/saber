package org.editice.saber.facade.test.code.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tinglang
 * @date 2022/7/10.
 */
public class T4 {

    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        List<Integer> res1 = new ArrayList<>();
        List<Integer> res2 = new ArrayList<>();
        for(int i=0;i<wordsDict.length;i++){
            String key = wordsDict[i];
            if(key.equals(word1)){
                res1.add(i);
            }

            if(key.equals(word2)){
                res2.add(i);
            }

        }

        int res = Integer.MAX_VALUE;
        for(int x:res1){
            for(int y:res2){
                res = Math.min(res,Math.abs(x-y));
            }
        }
        return res;
    }
}
