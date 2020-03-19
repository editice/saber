package framework;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Data
public class BizConfigSpec {

    List<String> supportedProductCodes;

    Map</* extension code */String, List<ProductPriority>> config;

    public void addProductPriorityConfig(String productCode, String extensionCode, int priority) {
        ProductPriority productPriority = new ProductPriority();
        productPriority.setProductCode(productCode);
        productPriority.setPriority(priority);
        if (config == null) {
            config = Maps.newHashMap();
        }
        if (config.get(extensionCode) != null) {
            config.get(extensionCode).add(productPriority);
        } else {
            config.put(extensionCode, Lists.newArrayList(productPriority));
        }
    }

    public void addSupportedProductCode(String productCode) {
        if (supportedProductCodes == null) {
            supportedProductCodes = Lists.newArrayList();
        }

        supportedProductCodes.add(productCode);
    }

    public int getProductPriority(String productCode, String extensionCode) {
        for (ProductPriority productPriority : config.get(extensionCode)) {
            if (productPriority.getProductCode().equals(productCode)) {
                return productPriority.getPriority();
            }
        }

        return 0;
    }

    @Data
    class ProductPriority {
        String productCode;

        int priority;
    }
}
