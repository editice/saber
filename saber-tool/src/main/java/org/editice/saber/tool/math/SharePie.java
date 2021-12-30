package org.editice.saber.tool.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author tinglang
 * @date 2021/12/23.
 */
public class SharePie {

    /**
     * 指定特定key和分摊比例
     */
    protected final Map</* key */String, /* ratio */BigDecimal> sharesMap;

    public SharePie(Map<String, BigDecimal> shares) {
        this.sharesMap = shares;
    }

    public Map<String, BigDecimal> shareByDefault(BigDecimal amount) {
        return share(amount, 0, RoundingMode.DOWN);
    }

    public Map<String, BigDecimal> share(BigDecimal amount, int precision, RoundingMode roundingMode) {
        checkArgument(amount.compareTo(BigDecimal.ZERO) >= 0, "non-negative amount is required");

        BigDecimal restShareSum = sumShares(sharesMap);
        BigDecimal restAmount = amount;
        Map<String, BigDecimal> result = new HashMap<String, BigDecimal>(sharesMap.size());

        for (Map.Entry<String, BigDecimal> stringBigDecimalEntry : sharesMap.entrySet()) {
            BigDecimal share = stringBigDecimalEntry.getValue();
            if (restAmount.compareTo(BigDecimal.ZERO) <= 0) {
                result.put(stringBigDecimalEntry.getKey(), BigDecimal.ZERO);
                continue;
            }
            BigDecimal cut = share.multiply(restAmount).divide(restShareSum, precision, roundingMode);
            restShareSum = restShareSum.subtract(share);
            restAmount = restAmount.subtract(cut);
            result.put(stringBigDecimalEntry.getKey(), cut);
        }

        return result;
    }

    private BigDecimal sumShares(Map<String, BigDecimal> shares) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<String, BigDecimal> stringBigDecimalEntry : shares.entrySet()) {
            BigDecimal share = stringBigDecimalEntry.getValue();
            if (share.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalStateException("negative share amount is not allowed");
            }
            sum = sum.add(share);
        }
        return sum;
    }


    public static void main(String[] args) {

        //举例，月度计划回复场景：
        /**
         * 1. 蒙牛西安子公司，三个销售组织咸阳、西安、曲江，初始设置回复分摊比例为：7、2、1
         * 2. 子公司针对商品X，仅销售组织咸阳和西安有提报， 总计提报9999，实际回复8888
         * 3. 计算针对咸阳和西安回复分摊数额
         *
         *
         */
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("咸阳", new BigDecimal("7"));
        map.put("西安", new BigDecimal("2"));

        SharePie sharePie = new SharePie(map);

        System.out.println(sharePie.share(new BigDecimal("8888"), 0, RoundingMode.DOWN));

    }
}
