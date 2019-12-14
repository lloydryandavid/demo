package org.lrd.customerapi.config;


import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;


@Configuration
public class CustomerAppConfig {

    @Autowired
    Environment environment;

    @Autowired
    DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(environment.getProperty("liquibase.change-log"));
        liquibase.setDataSource(dataSource);
        return liquibase;
    }


}
