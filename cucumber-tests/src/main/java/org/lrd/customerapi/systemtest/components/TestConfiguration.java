package org.lrd.customerapi.systemtest.components;


import javax.sql.DataSource;


public interface TestConfiguration {

    public DataSource getDataSource();

    public String getFetchCustomerUrl();

    public String getAddCustomerUrl();

    public String getDeleteCustomerUrl();

}
