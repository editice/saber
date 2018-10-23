package org.editice.saber.tool.core;

import org.editice.saber.tool.core.util.FeatureCodec;
import org.editice.saber.tool.core.util.GaReflectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Modifier.isStatic;

/**
 * @author tinglang
 * @date 2018/10/22.
 */
public class ArgsParam {

    private int javaPid;
    private String targetIp;
    private int targetPort;
    private int connectTimeout = 6000;      // 连接超时时间(ms)
    private String agent;
    private String core;

    private final static FeatureCodec codec = new FeatureCodec(';', '=');

    /**
     * 序列化成字符串
     *
     * @return 序列化字符串
     */
    @Override
    public String toString() {

        final Map<String, String> map = new HashMap<String, String>();
        for (Field field : GaReflectUtils.getFields(ArgsParam.class)) {

            // 过滤掉静态类
            if (isStatic(field.getModifiers())) {
                continue;
            }

            // 非静态的才需要纳入非序列化过程
            try {
                map.put(field.getName(), newString(GaReflectUtils.getValue(this, field)));
            } catch (Throwable t) {
                //
            }

        }

        return codec.toString(map);
    }

    public static String newString(Object obj) {
        if (null == obj) {
            return "";
        }
        return obj.toString();
    }

    public static ArgsParam toParam(String args) {
        final ArgsParam argsParam = new ArgsParam();
        final Map<String, String> map = codec.toMap(args);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            final Field field = GaReflectUtils.getField(ArgsParam.class, entry.getKey());
            if (null != field
                    && !Modifier.isStatic(field.getModifiers())) {
                try {
                    GaReflectUtils.setValue(field, GaReflectUtils.valueOf(field.getType(), entry.getValue()), argsParam);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return argsParam;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public int getJavaPid() {
        return javaPid;
    }

    public void setJavaPid(int javaPid) {
        this.javaPid = javaPid;
    }

    public String getTargetIp() {
        return targetIp;
    }

    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    public int getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}
