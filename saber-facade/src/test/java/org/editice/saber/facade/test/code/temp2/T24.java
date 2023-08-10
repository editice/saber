package org.editice.saber.facade.test.code.temp2;

import org.apache.commons.lang3.StringUtils;
import org.editice.saber.facade.test.code.TestBase;
import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * @author tinglang
 * @date 2022/9/8.
 */
public class T24 implements TestBase {

    public boolean canPermutePalindrome(String s) {
        int[] arr = new int[26];
        for(char x: s.toCharArray()){
            arr[x-'a']++;
        }

        int jishuCnt = 0;
        for(int y:arr){
            jishuCnt += (y%2==0?0:1);
            if(jishuCnt>1){
                return false;
            }
        }

        return true;
    }

    @Test
    public void testSuspend(){
        new Thread(()->threadDoA()).start();

        new Thread(()->threadDoB()).start();

        new Thread(()->threadDoA()).start();

        new Thread(()->threadDoB()).start();
    }


    public static void threadDoA(){
        System.out.println("线程 - doA - "+Thread.currentThread().getId());

    }

    public static void threadDoB(){
        System.out.println("线程 - doB - "+Thread.currentThread().getId());
    }

    @Test
    @Override
    public void test() {
        String name = "#sZFyd96rd96r#Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2NjQyODA5NDkyNzY4MDIzNDQSIKh5uh+fdVTAds0/rRbPlgkN8sLe1MQVk3aqEz31wKlPGhKLdZx9I/KnN0/Vrw9pN4uBBCUiIJwAm/EFM3F6fRHIb9f8QMEpgqeNQahiOJdJeHy9RCP3KAUwAQ==&Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2NjQyODA5NDkyNzY4MDIzNDQSMFHlTW2uyxvKTSnYP8rH9SeqBehNmaLOUkNqKofO0D7nwiSCtG5e6+Lq843WnNE7choSNbRnevjlMlUCNJ4pZ4LbQzkvIiD5ozL4CP6WTnsVQ//p2y7+N0D92DL1VAmqQxFxpwCUoygFMAE=#1##";
        String phone = "$aOlu6gb6ci833T9xZKtnZfSWr8wu2nWT/niGx9/xNwI=$Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2NjQyODA5NDkyNzY4MDIzNDQSIGOIlV57KqgBvLPBUA8wPtKpu3JYJY52+mXTdw4zJ2XoGhJHUZmscbF8f0deTf6FyTRsB0oiIO+w/snibN+e6Gj/V4lSEGroMNOvjsZJ2CdJxwetoiXaKAUwAQ==&Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2NjQyODA5NDkyNzY4MDIzNDQSMIxaKyZMOFljZUs9HTt/ye/d8/iDqoowJBq9PqpGAeOpQybums9GD+JCvt87g7cy6BoSq4zditmeCL+PGqGPDQ8tsHM4IiCfL0tqTBlSZU7VmUf4AOGuR2uJfkp/xl4nCZrCWstPrCgFMAE=$1$$";
        String address = "~+PMZgHx7KnUGMP3baXvgknVm8ANXwrujqdvHwQ9SsZFyd96rd96rDvfaqT+0s8Srzm3LSKErqdvHwQ9S0gzTMTUc0gzTWFjHRxKPMTUcWFjH0gzTT/VDT/VDT/VDDvfaID5nFZFkccxstpQtqdvHwQ9SXtCXkM1IiS4siZJM6FdWXP5ziZJM791utpQt+PMZgHx7KnUGMP3baXvgDvfacxk6Im37ccxsXjd6qdvHwQ9SjUwA8hfAKnUGHTCKq66AHXcntpQt0gzTzm3LS61yIdFL31X+0gzT~Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2NjQyODA5NDkyNzY4MDIzNDQSwAEslREckaWjbBjQelFFSRdNLoimGU9OyxHGeCd2fvh0vXaTKJUbsJcASx3N9JA2mF9cCgNTO2FDFUWBCNpizAgUtJrTBcJ5qTYzllozxLVq1AJslZ2QLQiuF6nj1cKMKSU8cyeYx51g7k9O69qlyiF8fopgiCRMMehzexgmXrApZjNs19kzqDWZAIFMmIp6Fq1N8TjrAsQd0f+oLyRyTKafbtBMlpGR0C0sfFv84V5OD59/NAzNSJAQ8bfQyR7XnPQaEsYSw+gKIwsUQqmsjrP2T4np0CIgGpQk2NgmoxaoDUtiEWnjIsWWxMxyq+eJrfxiia3RpFgoBTAB&Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2NjQyODA5NDkyNzY4MDIzNDQSMDFeOYkUa8A8c99S2ve/ADN7f54KHTMpqvGnU/gkIXQsj1l2NV4Vsp5EfEqAAiZs+hoSzH1yPJ2ED/QkpNdQbC2WMmuiIiCuaDAJLWgVs3OzW8eANYewCKuYUqkk1BBKrTZvmQ2ybigFMAE=~1~~";

        System.out.println(extractIndex(name));
        System.out.println(extractIndex(phone));
        System.out.println(extractIndex(address));
    }


    /**
     * 获取oaid
     */
    public static String extractIndex(String encryptedData) {
        if (encryptedData == null || encryptedData.length() < 4) {
            return null;
        }
        char sepInData = encryptedData.charAt(0);
        if (encryptedData.charAt(encryptedData.length() - 2) != sepInData) {
            return null;
        }
        String[] parts = StringUtils.split(encryptedData, sepInData);
        if (sepInData == '$' || sepInData == '#' || sepInData == '~' ) {
            return parts[0];
        }else {
            return parts[1];
        }
    }
}
