package org.lrd.customerapi.systemtest.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;


@Component
@Profile("test-component")
public class ComponentTestConfiguration implements TestConfiguration {

    @Autowired
    private Environment environment;

    @Override
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(environment.getProperty("test-component-config.datasource.url"));
        dataSource.setUsername(environment.getProperty("test-component-config.datasource.username"));
        dataSource.setPassword(environment.getProperty("test-component-config.datasource.password"));
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
