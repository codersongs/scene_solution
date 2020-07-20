package com.codersongs.scenesolution.uniqId.aop;

import com.codersongs.scenesolution.uniqId.annotation.DataSourceAnnotaion;
import com.codersongs.scenesolution.uniqId.config.DynamicDataSourceContextHolder;
import com.codersongs.scenesolution.uniqId.enums.DataSourceEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Aspect
@Component
public class DynamicDataSourceAspect {
    private static Map<Integer, String> CHOSE_DS = new ConcurrentHashMap<>();

    static {
        CHOSE_DS.put(0, DataSourceEnum.MULTISEQ1.getName());
        CHOSE_DS.put(1, DataSourceEnum.MULTISEQ2.getName());
    }

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DataSourceAnnotaion ds) throws Throwable {
        System.out.println("拦截到");
        DataSourceEnum dataSourcEnum = ds.name();
        //为ID的生成随机选择一个数据源
        if (dataSourcEnum == DataSourceEnum.MULTIRANDOM){
            Integer index = (int) System.currentTimeMillis() % CHOSE_DS.size();
            DynamicDataSourceContextHolder.setDataSourceRouterKey(CHOSE_DS.get(index));
        } else {
            DynamicDataSourceContextHolder.setDataSourceRouterKey(dataSourcEnum.getName());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, DataSourceAnnotaion ds) {
        System.out.println("生效");
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();

    }
}
