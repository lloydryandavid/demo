package org.lrd.customerapi.systemtest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;


public class CustomerApiTestConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private String host;

    @Autowired
    private String port;

    @Autowired
    private DataSource dataSource;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String host() {
        return env.getProperty("customer-api.host");
    }

    @Bean String port() { return env.getProperty("customer-api.port"); }

    @Bean
    @DependsOn({"host","port"})
    public String getCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", host, port,(env.getProperty("customer-api.endpoint.fetch-customer")));
    }

    @Bean
    @DependsOn({"host","port"})
    public String addCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", host, port, (env.getProperty("customer-api.endpoint.add-customer")));
    }

    @Bean
    @DependsOn({"host","port"})
    public String deleteCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", host, port, (env.getProperty("customer-api.endpoint.delete-customer")));
    }

    @Bean
    @DependsOn("postgreSQLContainer")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    @DependsOn("dataSource")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public FixedHostPortGenericContainer postgreSQLContainer() throws Exception {
        FixedHostPortGenericContainer postgreSQLContainer = new FixedHostPortGenericContainer<>("postgres:latest")
                .withEnv("POSTGRES_USER",env.getProperty("spring.datasource.username"))
                .withEnv("POSTGRES_PASSWORD",env.getProperty("spring.datasource.password"))
                .withEnv("POSTGRES_DB",env.getProperty("spring.datasource.database"))
                .withFixedExposedPort(
                        Integer.parseInt(env.getProperty("spring.datasource.host-port")),
                        Integer.parseInt(env.getProperty("spring.datasource.container-port")));
        postgreSQLContainer.start();
        while(!postgreSQLContainer.isRunning()) {
            Thread.sleep(500);
        }
        return postgreSQLContainer;
    }

}
