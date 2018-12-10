package org.editice.saber;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @author tinglang
 * @date 2018/12/7.
 */
@Order(1)
public class SaberApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("initializer start ...");
        System.out.println("application context name: "+ applicationContext.getApplicationName());
        System.out.println("application context display name: "+ applicationContext.getDisplayName());

        System.out.println("application context bean count: "+ applicationContext.getBeanDefinitionCount());
        System.out.println("bean list >>>>>");
        for(String beanName: applicationContext.getBeanDefinitionNames()){
            System.out.println(beanName);
        }

        System.out.println("initializer end ...");

    }
}
