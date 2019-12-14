package org.lrd.customerapi.systemtest.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;


@Component
@Profile("local")
public class ComponentTestConfiguration implements TestConfiguration {

    @Autowired
    private Environment environment;

    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver"));
        return dataSource;
    }

    @Override
    public String getFetchCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", getHost(), getPort(),(environment.getProperty("test-component-config.customer-api.endpoint.fetch-customer")));
    }

    @Override
    public String getAddCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", getHost(), getPort(), (environment.getProperty("test-component-config.customer-api.endpoint.add-customer")));
    }

    @Override
    public String getDeleteCustomerUrl() {
        return String.format("%s%s:%s%s", "http://", getHost(), getPort(), (environment.getProperty("test-component-config.customer-api.endpoint.delete-customer")));
    }

    private String getHost() {
        return environment.getProperty("test-component-config.customer-api.host");
    }

    private String getPort() {
        return environment.getProperty("test-component-config.customer-api.port");
    }

}
