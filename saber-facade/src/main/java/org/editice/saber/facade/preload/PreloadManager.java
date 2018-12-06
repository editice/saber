package org.editice.saber.facade.preload;

import org.editice.saber.facade.conf.SaberSysConf;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author tinglang
 * @date 2018/12/6.
 */
@Component
public class PreloadManager implements CommandLineRunner {

    @Resource
    private SaberSysConf saberSysConf;

    public void run(String... strings) throws Exception {
        System.out.println("saber conf - preload ...");
        printConf("appEnv", saberSysConf.getAppEnv());
        printConf("appDesc", saberSysConf.getAppDesc());
    }

    private void printConf(String key, Object val) {
        System.out.println("" + key + ": <<< " + val + " >>>");
    }
}
