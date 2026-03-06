package com.sachet.payment_service.config.database;

import com.sachet.payment_service.config.BeanConfigurationBase;
import com.sachet.payment_service.config.EnvironmentConfiguration;
import com.sachet.payment_service.config.model.DatabaseConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration extends BeanConfigurationBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfiguration.class);
    public DataSourceConfiguration(AutowireCapableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Bean(name = "paymentDS")
    public DataSource getProductDataSource(EnvironmentConfiguration environmentConfiguration) {
        DatabaseConfiguration databaseConfiguration = environmentConfiguration.getDatabaseConfiguration();
        LOGGER.info("The DatabaseConfiguration: {}", databaseConfiguration);
        return setUpDataBaseConnection(databaseConfiguration);
    }

    private DataSource setUpDataBaseConnection(DatabaseConfiguration databaseConfiguration) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseConfiguration.getUrl());
        hikariConfig.setUsername(databaseConfiguration.getUserName());
        hikariConfig.setPassword(databaseConfiguration.getPassword());
        hikariConfig.setMaximumPoolSize(databaseConfiguration.getMaxPoolSize());
        hikariConfig.setDriverClassName(databaseConfiguration.getDriverClassName());
        hikariConfig.setConnectionTimeout(databaseConfiguration.getConnectionTimeOut());
        return new HikariDataSource(hikariConfig);
    }
}
