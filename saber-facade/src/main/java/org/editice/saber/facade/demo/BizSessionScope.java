package org.editice.saber.facade.demo;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
public abstract  class BizSessionScope<Resp, Exc1 extends Throwable> extends OnceInvoker<Resp, Exc1, Exc1> {

    private static Object entry = null;


}
