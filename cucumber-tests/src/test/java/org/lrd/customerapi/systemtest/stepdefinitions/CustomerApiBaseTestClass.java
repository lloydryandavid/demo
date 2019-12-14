package org.lrd.customerapi.systemtest.stepdefinitions;


import org.lrd.customerapi.systemtest.testcontext.CucumberTestContext;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class CustomerApiBaseTestClass {

    @Autowired
    protected String addCustomerUrl;

    @Autowired
    protected String getCustomerUrl;

    @Autowired
    public CucumberTestContext testContext;

}
