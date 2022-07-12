package org.editice.saber.facade.test.code.lc;

/**
 * @author tinglang
 * @date 2022/7/11.
 */
public class T7 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for(char x: magazine.toCharArray()){
            arr[x-'a']++;
        }

        for(char y:ransomNote.toCharArray()){
            int pos = y-'a';
            if(arr[pos]==0){
                return false;
            }

            arr[pos]--;
        }

        return true;
    }
}
