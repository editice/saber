package org.editice.saber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tinglang
 * @date 17/7/19.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(Application.class);

        //add initializers
        springApplication.addInitializers(new SaberApplicationContextInitializer());

        //add listeners
        springApplication.addListeners(new SaberApplicationDemoListener());

        //run application
        springApplication.run(args);
    }
}
