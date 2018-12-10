package org.editice.saber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author tinglang
 * @date 17/7/19.
 */
@SpringBootApplication
@ImportResource({"classpath*:saber-beans.xml"})
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
