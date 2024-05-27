package org.editice.saber.facade.demo;

/**
 * @author tinglang
 * @date 2024/4/23.
 */
public @interface Extension {

    /**
     * 扩展点code命名规范：{业务活动code}_ext_{xxxx}，通过前缀自动关联到业务活动上去。【注意，不能包括空格、逗号、换行、点号，以及其他特殊符号】。
     *
     * @return
     */
    String code();

    /**
     * 作者 & 时间
     *
     * @return
     */
    String author();// default "";

    /**
     * 业务描述，不用很多文字
     *
     * @return
     */
    String desc();// default "";

    String dependencies() default "";
}
