package org.lrd.customerapi.systemtest.components;


import org.lrd.customerapi.systemtest.testcontext.CucumberTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;


public class TestConfiguration {

    @Autowired
    private Environment environment;
    
    @Autowired
    private DataSource dataSource;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public String getCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", getHost(), getPort(),(environment.getProperty("customer-api.endpoint.fetch-customer")));
    }

    @Bean
    public String addCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", getHost(), getPort(), (environment.getProperty("customer-api.endpoint.add-customer")));
    }

    @Bean
    public String deleteCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", getHost(), getPort(), (environment.getProperty("customer-api.endpoint.delete-customer")));
    }

    @Bean
    @DependsOn({"restTemplate", "jdbcTemplate"})
    public CucumberTestContext testContext() {
        return new CucumberTestContext();
    }

    private String getHost() {
        return environment.getProperty("customer-api.host");
    }

    private String getPort() {
        return environment.getProperty("customer-api.port");
    }

}
