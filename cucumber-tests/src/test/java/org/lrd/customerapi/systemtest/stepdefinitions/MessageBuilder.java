package org.lrd.customerapi.systemtest.stepdefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.lrd.customerapi.systemtest.testcontext.CucumberTestContext;
import org.lrd.customerapi.systemtest.utils.MessageBuilderUtil;
import java.io.IOException;


public class MessageBuilder extends CustomerApiBaseTestClass {

    @Given("I have a payload containing the following properties: {string} {string}")
    public void createPayload(String firstName, String lastName) {
        testContext.payload = MessageBuilderUtil.getPayload(firstName, lastName);
    }

    @And("^I have set the message headers (.*)$")
    public void createHeaders(String stringHeaders) throws IOException  {
        testContext.httpHeaders = MessageBuilderUtil.getHeaders(stringHeaders);
    }

}
