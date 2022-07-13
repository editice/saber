package org.editice.saber.facade.test.code.temp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tinglang
 * @date 2022/7/7.
 */
public class T2 {
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> arr = new ArrayList<>();
        int len = currentState.length();
        if(len<=1){
            return arr;
        }
        for(int i=0;i<len-1;i++){
            if("++".equals(currentState.substring(i,i+2))){
                arr.add(currentState.substring(0,i)+"--"+currentState.substring(i+2,len));
            }
        }

        return arr;
    }
}
