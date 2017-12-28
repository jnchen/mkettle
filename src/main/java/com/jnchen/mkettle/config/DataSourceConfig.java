package com.jnchen.mkettle.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.base.Preconditions;
import com.jnchen.mkettle.properties.DbInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caojingchen on 2017/12/28.
 */
@Configuration
@EnableConfigurationProperties(DbInfoProperties.class)
public class DataSourceConfig {

    @Autowired
    private DbInfoProperties dbInfoProperties;

    private static final String DEFAULT_MAX_ACTIVE = "50";

    @Bean
    @Primary
    public DruidDataSource dataSource(){
        String url = Preconditions.checkNotNull(dbInfoProperties.getUrl(),"db.url is null");
        String username = Preconditions.checkNotNull(dbInfoProperties.getUsername(),"db.username is null");
        String password = Preconditions.checkNotNull(dbInfoProperties.getPassword(), "db.password is null");
        String maxActive = dbInfoProperties.getMaxActive()==null?DEFAULT_MAX_ACTIVE: dbInfoProperties.getMaxActive().toString();

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);


        dataSource.setPassword(password);
        dataSource.setMaxActive(Integer.parseInt(maxActive));
        //设置datasource监控
        List<Filter> filterList=new ArrayList<Filter>();
        filterList.add(statFilter());
        dataSource.setProxyFilters(filterList);
        return dataSource;
    }
    @Bean
    ServletRegistrationBean DruidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //添加初始化参数:默认为数据库名密码
        servletRegistrationBean.addInitParameter("loginUsername", dbInfoProperties.getUsername());
        servletRegistrationBean.addInitParameter("loginPassword", dbInfoProperties.getPassword());
        return servletRegistrationBean;
    }

    @Bean
    public StatFilter statFilter(){
        StatFilter filter=new StatFilter();
        filter.setSlowSqlMillis(10000);
        filter.setLogSlowSql(true);
        return filter;
    }
}
