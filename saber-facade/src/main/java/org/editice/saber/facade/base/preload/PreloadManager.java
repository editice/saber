package org.editice.saber.facade.base.preload;

import org.editice.saber.facade.base.conf.SaberSysConf;
import org.editice.saber.facade.base.evaluator.TimeRecord;
import org.editice.saber.facade.demo.SaberBeanPostProcessorDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tinglang
 * @date 2018/12/6.
 */
@Component
public class PreloadManager implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PreloadManager.class);

    @Resource
    private SaberSysConf saberSysConf;

    @Resource
    private SaberBeanPostProcessorDemo saberBeanPostProcessorDemo;

    public void run(String... strings) throws Exception {
        logger.warn("saber conf:" + saberSysConf.toString());

        List<TimeRecord> records = saberBeanPostProcessorDemo.getSortTimeRecords();
        for (TimeRecord record : records) {
            logger.warn("cost:[" + record.getTimeCost() + "ms]" + "beanName:[" + record.getBeanName() + "]");
        }
    }
}
