package framework;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Data
public class ActionCodes {

    List<String> codes = Lists.newArrayList();

    public static final ActionCodes DEFAULT = null;

    public void addCode(String actionCode){
        if(codes==null){
            codes = Lists.newArrayList();
        }

        codes.add(actionCode);
    }

    public boolean exist(String actionCode){
        if(codes.contains(actionCode)){
            return true;
        }

        return false;
    }

}
