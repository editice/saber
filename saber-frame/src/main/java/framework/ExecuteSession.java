package framework;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
public class ExecuteSession extends FrameSession {

    public static ExecuteSession create(ExecuteSessionParam param){
        ExecuteSession executeSession = new ExecuteSession();
        executeSession.setParam(param);
        executeSession.create();
        return executeSession;
    }

    public static ExecuteSessionParam getExecuteParam(){
        ExecuteSession executePaySession = getCurrentSession();
        if(null == executePaySession){
            return null;
        }
        return (ExecuteSessionParam) executePaySession.getParam();
    }

    public static ExecuteSession getCurrentSession(){
        return (ExecuteSession) currentSession();
    }
}
