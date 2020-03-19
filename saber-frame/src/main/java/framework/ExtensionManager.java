package framework;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tinglang
 * @date 2020/3/18.
 */
public class ExtensionManager {

    private static ExtensionManager instance;

    private static final Object locker = new Object();

    private static Map</* bizCode */String, BizConfigSpec> bizConfigSpecMap = new ConcurrentHashMap<>();

    private static Map</* productCode */String, /* bizCode */List<String>> productRelationMap = new ConcurrentHashMap<>();

    //TODO demo用，真正投入生产使用时要拆分开来，避免冗余过多实例
    private static Map</* ext code */String, Map</* bizCode */String, /* ext list including app & product */List<Pair<IExt>>>> extensionMap = new ConcurrentHashMap<>();

    public List<IExt> findExtension(String extensionCode, String bizCode) {
        Map<String, List<Pair<IExt>>> extensions = extensionMap.get(extensionCode);
        if (extensions != null) {
            List<Pair<IExt>> pairs = extensions.get(bizCode);

            //TODO demo用，真正投入生产使用时不合适这么写
            Collections.sort(pairs);
            List<IExt> iExts = Lists.newArrayList();
            for (Pair<IExt> pair : pairs) {
                iExts.add(pair.getObject());
            }

            System.out.println("执行扩展点：" + extensionCode);
            System.out.println("执行业务身份：" + bizCode);
            System.out.println("扩展点顺序：" + iExts);
            return iExts;
        }

        return null;
    }

    //TODO 模拟包扫描过程，这里偷懒，随便写写
    public void scanExtensions(List<Class> clazzs) throws Exception {
        for (Class clazz : clazzs) {
            Extension extensionAnnotation = (Extension) clazz.getAnnotation(Extension.class);
            if (extensionAnnotation != null) {
                //若属于扩展点，则扫描具体的method
                findExtensionMethodAndRegist(clazz, clazz, extensionAnnotation);
            }
        }
    }

    public void scanConfigSpec(List<BizConfig> bizConfigs) {
        for (BizConfig bizConfig : bizConfigs) {
            bizConfigSpecMap.put(bizConfig.getBizCode(), bizConfig.getBizConfigSpec());

            for (String productCode : bizConfig.getBizConfigSpec().getSupportedProductCodes()) {
                if (productRelationMap.get(productCode) == null) {
                    productRelationMap.put(productCode, Lists.newArrayList(bizConfig.getBizCode()));
                } else {
                    productRelationMap.get(productCode).add(bizConfig.getBizCode());
                }
            }
        }
    }


    public static ExtensionManager getInstance() {
        if (null == instance) {
            synchronized (locker) {
                if (null == instance) {
                    instance = new ExtensionManager();
                }
            }
        }
        return instance;
    }

    private static void findExtensionMethodAndRegist(Class instance, Class clazz, Extension extensionAnnotation) throws Exception {
        if (clazz == null || clazz.getMethods() == null) {
            return;
        }

        String bizCode = extensionAnnotation.bizCode();
        String productCode = extensionAnnotation.productCode();


        boolean product = StringUtils.isNotEmpty(productCode);
        List<String> bizCodeSets = Lists.newArrayList();
        if (StringUtils.isEmpty(bizCode) && StringUtils.isNotEmpty(productCode)) {
            bizCodeSets.addAll(productRelationMap.get(productCode));
        } else {
            bizCodeSets.add(bizCode);
        }

        for (Method method : clazz.getMethods()) {
            ExtensionIdentity extensionIdentityAnnotaion = method.getAnnotation(ExtensionIdentity.class);
            if (extensionIdentityAnnotaion != null) {
                String extensionCode = extensionIdentityAnnotaion.code();

                int priority = 0;//业务code默认为0
                if (extensionMap.get(extensionCode) != null) {
                    Map<String, List<Pair<IExt>>> map = extensionMap.get(extensionCode);
                    for (String tmp : bizCodeSets) {
                        if (product) {
                            priority = bizConfigSpecMap.get(tmp).getProductPriority(productCode, extensionCode);
                        }

                        if (map.get(tmp) != null) {
                            map.get(tmp).add(new Pair<>((IExt) instance.newInstance(), priority));
                        } else {
                            map.put(tmp, Lists.newArrayList(new Pair<>((IExt) instance.newInstance(), priority)));
                        }
                    }

                } else {
                    Map<String, List<Pair<IExt>>> map = Maps.newConcurrentMap();
                    extensionMap.put(extensionCode, map);
                    for (String tmp : bizCodeSets) {
                        if (product) {
                            priority = bizConfigSpecMap.get(tmp).getProductPriority(productCode, extensionCode);
                        }

                        map.put(tmp, Lists.newArrayList(new Pair<>((IExt) instance.newInstance(), priority)));
                    }
                }
            }
        }

        if (clazz.getInterfaces() != null) {
            for (Class clz : clazz.getInterfaces()) {
                findExtensionMethodAndRegist(instance, clz, extensionAnnotation);
            }
        }


    }


}
