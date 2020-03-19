package framework;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
public abstract class ActionSessionScope<Resp, Exc1 extends Throwable> extends OnceInvoker<Resp, Exc1, Exc1> {

    private static Entry entry = newEntryInstance();

    private String actionCode;//默认为空，允许不指定

    public ActionSessionScope() {
    }

    public ActionSessionScope(String actionCode) {
        this.actionCode = actionCode;
    }

    @Override
    protected Entry getEntry() {
        return entry;
    }

    @Override
    protected Resp _execute() throws Exc1, Exc1 {
        System.out.println(String.format("-----------> 业务活动: {%s}<-----------", actionCode));
        return _exec();
    }

    protected abstract Resp _exec() throws Exc1, Exc1;

    @Override
    public Class getClassName() {
        return ActionSessionScope.class;
    }

    @Override
    protected void entry() {
        ExecuteSessionParam param = new ExecuteSessionParam();
        param.setActionCode(actionCode);
        ExecuteSession.create(param);
    }

    @Override
    protected final void exit() {
        FrameSession.destroySession();
    }
}
