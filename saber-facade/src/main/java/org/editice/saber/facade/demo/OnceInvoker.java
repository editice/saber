package org.editice.saber.facade.demo;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
public abstract class OnceInvoker<Resp, Exc1 extends Throwable, Exc2 extends Throwable> {

    protected abstract Resp _execute() throws Exc1, Exc2;

    public Resp invoke() throws Exc1, Exc2 {
        try {

            return _execute();
        } finally {
            return  null;
        }

        }
    }
