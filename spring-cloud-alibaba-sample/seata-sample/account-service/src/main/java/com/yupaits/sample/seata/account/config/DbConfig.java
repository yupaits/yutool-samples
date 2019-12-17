package com.yupaits.sample.seata.account.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

/**
 * @author yupaits
 * @date 2019/8/28
 */
@Configuration
public class DbConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public DbConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource storageDataSource() throws SQLException {
        Environment env = applicationContext.getEnvironment();
        String ip = env.getProperty("mysql.server.ip");
        String port = env.getProperty("mysql.server.port");
        String dbName = env.getProperty("mysql.db.name");
        String username = env.getProperty("mysql.user.name");
        String password = env.getProperty("mysql.user.password");
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://" + ip + ":" + port + "/" + dbName + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setInitialSize(0);
        druidDataSource.setMaxActive(180);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setMinIdle(0);
        druidDataSource.setValidationQuery("Select 'x' from DUAL");
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(25200000);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(1800);
        druidDataSource.setLogAbandoned(true);
        druidDataSource.setFilters("mergeStat");
        return druidDataSource;
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSourceProxy dataSourceProxy) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceProxy);
        jdbcTemplate.update("delete from account_tbl where user_id = 'U100001'");
        jdbcTemplate.update("insert into account_tbl(user_id, money) values ('U100001', 10000)");
        return jdbcTemplate;
    }
}
