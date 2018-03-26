package org.editice.saber.facade.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tinglang
 * @date 2017/10/31.
 */
public class JunitTest {

    @Test
    public void testNullForeach(){
        List<String> list = Lists.newArrayList();

        list.add("11");
        list.add("22");
        list.add(null);
        list.add("33");
        for(String s: list){
            System.out.println(s);
        }
    }

    /**
     * 测试使用guava的cache存储计数器来测试限流
     */
    @Test
    public void testLimitRate() throws ExecutionException {
        LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long aLong) throws Exception {
                        return new AtomicLong(0);
                    }
                });

        long limit =1000;
        int i = 0;
        while(true){
            long currentSecs = System.currentTimeMillis()/1000;
            if(counter.get(currentSecs).incrementAndGet() > limit){
                System.out.println("限流!: "+ currentSecs);
                continue;
            }

            if(i++ > 100000){
                break;
            }
        }
    }

}
