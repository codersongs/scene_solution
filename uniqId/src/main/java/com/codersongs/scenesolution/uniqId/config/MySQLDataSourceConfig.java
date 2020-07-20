package com.codersongs.scenesolution.uniqId.config;

import com.codersongs.scenesolution.uniqId.enums.DataSourceEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MySQLDataSourceConfig {

    /**
     * 默认数据源
     * @return
     */
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public DataSource dataSourceDefault(){
        DynamicDataSourceContextHolder.dataSourceIds.put("dataSource", "EXISTS");
        return DataSourceBuilder.create().build();
    }

    /**
     * 多数据源一
     * @return
     */
    @Bean("multiDataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.multiseq1")
    public DataSource dataSourceMulti1(){
        DynamicDataSourceContextHolder.dataSourceIds.put("multiDataSource1", "EXISTS");
        return DataSourceBuilder.create().build();
    }

    /**
     * 多数据源二
     * @return
     */
    @Bean("multiDataSource2")
    @ConfigurationProperties(prefix = "spring.datasource.multiseq2")
    public DataSource dataSourceMulti2(){
        DynamicDataSourceContextHolder.dataSourceIds.put("multiDataSource2", "EXISTS");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicRoutingDataSource")
    @Primary
    public DynamicRoutingDataSource dynamicDataSource(@Qualifier("dataSource")DataSource dataSource, @Qualifier("multiDataSource1")DataSource multiDataSource1, @Qualifier("multiDataSource2")DataSource multiDataSource2){
        DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
        targetDataSources.put(DataSourceEnum.DEFAULT.getName(), dataSource);
        targetDataSources.put(DataSourceEnum.MULTISEQ1.getName(), multiDataSource1);
        targetDataSources.put(DataSourceEnum.MULTISEQ2.getName(), multiDataSource2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        return  dynamicDataSource;
    }
}
