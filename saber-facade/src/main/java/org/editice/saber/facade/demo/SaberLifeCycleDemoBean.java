package org.editice.saber.facade.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author tinglang
 * @date 2018/12/10.
 */
public class SaberLifeCycleDemoBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(SaberLifeCycleDemoBean.class);

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        logger.warn("[demo bean refresh] set property");
        this.userName = userName;
    }

    //    spring bean的初始化方法是：构造器-》postConstruct注解-》afterpropertiesSet方法-》init-method中指定的方法

    public SaberLifeCycleDemoBean() {
        logger.warn("[demo bean refresh] constructor");
    }

    @PostConstruct
    public void postConstruct() {
        logger.warn("[demo bean refresh] postConstruct");
    }


    public void init() {
        logger.warn("[demo bean refresh] init-method");
    }

    @PreDestroy
    public void preDestroy() {
        logger.warn("[demo bean refresh] preDestroy");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.warn("[demo bean refresh] setBeanFactory=" + beanFactory);
    }

    @Override
    public void setBeanName(String name) {
        logger.warn("[demo bean refresh] setBeanName=" + name);
    }

    @Override
    public void destroy() throws Exception {
        logger.warn("[demo bean refresh] destroy bean");
    }

    /**
     * 无需反射的实现方式，但是不能有效解耦；而使用postConstruct注解或者使用init-method方式则使用反射，但是可以有效解耦
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.warn("[demo bean refresh] afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.warn("[demo bean refresh] setApplicationContext");
    }
}
