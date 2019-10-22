package org.lrd.customerapi.systemtest.config;


import org.lrd.customerapi.systemtest.components.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.FixedHostPortGenericContainer;


@ComponentScan("org.lrd.customerapi.systemtest.components")
public class CustomerApiTestConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private TestConfiguration testConfiguration;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String getCustomerUrl() {
        return testConfiguration.getFetchCustomerUrl();
    }

    @Bean
    public String addCustomerUrl() {
        return testConfiguration.getAddCustomerUrl();
    }

    @Bean
    public String deleteCustomerUrl() {
        return testConfiguration.getDeleteCustomerUrl();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(testConfiguration.getDataSource());
    }

    @Bean
    @Profile("test-component")
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
