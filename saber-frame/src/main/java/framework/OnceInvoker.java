package framework;


/**
 * 确保一次调用栈entry,exit 只调一次
 *
 * @author Rocky.
 * @since 15/12/29
 */
public abstract class OnceInvoker<Resp, Exc1 extends Throwable, Exc2 extends Throwable> {

    protected static Entry newEntryInstance() {
        return new EntryImpl();
    }

    protected abstract Entry getEntry();

    protected abstract Resp _execute() throws Exc1, Exc2;

    public abstract Class getClassName();

    @SuppressWarnings("unchecked")
    public Resp invoke() throws Exc1, Exc2 {
        try {
            getEntry().get().entryCountIncrement();
            if (getEntry().get().getEntryCount() == 1) {
                entry();
            }

            try {
                return _execute();
            } finally {
            }

        } finally {
            getEntry().get().entryCountDecrement();
            if (getEntry().get().getEntryCount() == 0) {
                getEntry().clean();
                exit();
            }
        }
    }

    protected abstract void entry();

    protected abstract void exit();


    protected interface Entry {

        int getEntryCount();

        void entryCountIncrement();

        void clean();

        void entryCountDecrement();

        Entry get();
    }

    private static class EntryImpl extends ThreadLocal<EntryImpl> implements Entry {

        private int count = 0;

        @Override
        protected EntryImpl initialValue() {
            return new EntryImpl();
        }

        @Override
        public int getEntryCount() {
            return count;
        }

        @Override
        public void entryCountIncrement() {
            count++;
        }

        @Override
        public void clean() {
            remove();
        }

        @Override
        public void entryCountDecrement() {
            count--;
        }
    }
}