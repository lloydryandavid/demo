package org.lrd.customerapi.systemtest.stepdefinitions;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.lrd.customerapi.systemtest.model.GetCustomerResponseMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import java.util.Map;


public class MessageSender extends CustomerApiBaseTestClass {

    @When("I send this message to the add customer path")
    public void addCustomer() {
        testContext.addCustomerResponseEntity = testContext.restTemplate.exchange(
                addCustomerUrl,
                HttpMethod.POST,
                new HttpEntity(testContext.payload, testContext.httpHeaders),
                Map.class
        );
    }

    @Then("the http status code of the add customer response message must be 200 OK")
    public void verifyAddCustomerMessageResponseStatusCode200OK() {
        Assert.assertEquals(HttpStatus.OK, testContext.addCustomerResponseEntity.getStatusCode());
    }

    @When("I retrieve the customer {string} {string} using the get customer path")
    public void getCustomer(String firstName, String lastName) {
        String getUrl = String.format("%sfirstName=%s&lastName=%s",getCustomerUrl, firstName, lastName);
        testContext.getCustomerResponseEntity = testContext.restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                new HttpEntity<>("", new HttpHeaders()),
                GetCustomerResponseMessage.class
        );
    }

    @Then("the {string} {string} must be retrieved")
    public void verifyCustomer(String expectedFirstName, String expectedLastName) throws Exception {
        Assert.assertEquals(expectedFirstName, testContext.getCustomerResponseEntity.getBody().getFirstName());
        Assert.assertEquals(expectedLastName,  testContext.getCustomerResponseEntity.getBody().getLastName());
    }

}
