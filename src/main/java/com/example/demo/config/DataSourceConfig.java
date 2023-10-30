package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.demo.model.mapper")
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;  // 自動注入 Spring 配置的數據源

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        // 其他 MyBatis 配置（如 mapperLocations、typeHandlers）可以在這裡進行設置
        return sqlSessionFactory.getObject();
    }
}
