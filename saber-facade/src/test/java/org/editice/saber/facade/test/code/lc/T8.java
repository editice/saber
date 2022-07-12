package org.editice.saber.facade.test.code.lc;

/**
 * @author tinglang
 * @date 2022/7/11.
 */
public class T8 {

    public char findTheDifference(String s, String t) {
        if(s.length()==0){
            return t.charAt(0);
        }

        int[] arr = new int[26];
        for(char x: s.toCharArray()){
            arr[x-'a']++;
        }

        for(char y:t.toCharArray()){
            int pos = y-'a';
            if(arr[pos]==0){
                return y;
            }

            arr[pos]--;
        }

        return t.charAt(0);
    }
}
