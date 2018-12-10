package org.editice.saber.facade.demo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.editice.saber.facade.base.evaluator.TimeRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author tinglang
 * @date 2018/12/10.
 */
@Component
public class SaberBeanPostProcessorDemo implements BeanPostProcessor {

    private static Logger logger = LoggerFactory.getLogger(SaberBeanPostProcessorDemo.class);

    private Map<String/* bean name*/, Long/* start time unit */> startTimeRecords = Maps.newHashMap();

    private Map<String/* bean name */, Long/* end time unit */> endTimeRecords = Maps.newHashMap();

    private List<TimeRecord> records = Lists.newArrayList();


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "lifeCycleDemoBean")) {
            logger.warn("[demo bean refresh] bean post processor before init! beanName=" + beanName);
        }
        startTimeRecords.put(beanName, System.currentTimeMillis());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "lifeCycleDemoBean")) {
            logger.warn("[demo bean refresh] bean post processor after init! beanName=" + beanName);
        }
        endTimeRecords.put(beanName, System.currentTimeMillis());
        return bean;
    }

    public List<TimeRecord> getSortTimeRecords() {
        if (!CollectionUtils.isEmpty(records)) {
            return records;
        }

        for (String beanName : endTimeRecords.keySet()) {

            Long startTime = startTimeRecords.get(beanName);
            if (startTime != null) {
                TimeRecord timeRecord = TimeRecord.builder()
                        .timeCost(endTimeRecords.get(beanName) - startTime)
                        .beanName(beanName).build();
                records.add(timeRecord);
            }
        }

        Collections.sort(records);
        return records;
    }




}
