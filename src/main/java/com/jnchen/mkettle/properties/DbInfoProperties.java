package com.jnchen.mkettle.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by caojingchen on 2017/12/28.
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DbInfoProperties {
    //驱动
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Boolean isEncrypt;

    public int initialSize;
    public int minIdle;
    public Integer maxActive;
    public int maxWait;
    public String validationQuery;
    public boolean testOnBorrow;
    public boolean testOnReturn;
    public boolean testWhileIdle;
    public int timeBetweenEvictionRunsMillis;
    public int minEvictableIdleTimeMillis;
    public String filters;
    public boolean poolPreparedStatements;
    public int maxPoolPreparedStatementPerConnectionSize;


}
