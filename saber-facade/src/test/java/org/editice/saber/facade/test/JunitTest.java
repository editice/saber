package org.editice.saber.facade.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tinglang
 * @date 2017/10/31.
 */
public class JunitTest {

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
