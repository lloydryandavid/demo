package org.lrd.customerapi.systemtest.components;


import org.rnorth.ducttape.unreliables.Unreliables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.FixedHostPortGenericContainer;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;


public class CustomerApiTestConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    @Profile({"local"})
    public FixedHostPortGenericContainer postgreSQLContainer() throws Exception {
        FixedHostPortGenericContainer postgreSQLContainer = new FixedHostPortGenericContainer<>("postgres:latest")
                .withEnv("POSTGRES_USER",env.getProperty("spring.datasource.username"))
                .withEnv("POSTGRES_PASSWORD",env.getProperty("spring.datasource.password"))
                .withEnv("POSTGRES_DB",env.getProperty("spring.datasource.database"))
                .withFixedExposedPort(
                        Integer.parseInt(env.getProperty("spring.datasource.host-port")),
                        Integer.parseInt(env.getProperty("spring.datasource.container-port")));
        postgreSQLContainer.start();
        Unreliables.retryUntilSuccess(10, TimeUnit.SECONDS, () -> {
            JdbcTemplate j = new JdbcTemplate(dataSource());
            j.execute("SELECT 1 + 1;");
            return j;
        });
        return postgreSQLContainer;
    }

}
