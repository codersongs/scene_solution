package com.codersongs.scenesolution.uniqId.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DynamicDataSourceContextHolder {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);
    /**
     * 存储已经注册的数据源的key
     */
    public static Map<String, String> dataSourceIds = new ConcurrentHashMap<>();

    /**
     * 线程级别的私有变量
     */
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static String getDataSourceRouterKey () {
        return HOLDER.get() == null ? "dataSource" : HOLDER.get();
    }

    public static void setDataSourceRouterKey (String dataSourceRouterKey) {
        logger.info("切换至{}数据源", dataSourceRouterKey);
        HOLDER.set(dataSourceRouterKey);
    }

    /**
     * 设置数据源之前一定要先移除
     */
    public static void removeDataSourceRouterKey () {
        HOLDER.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceKey
     * @return
     */
    public static boolean containsDataSource(String dataSourceKey){
        return dataSourceIds.containsKey(dataSourceKey);
    }
}
