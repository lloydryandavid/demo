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
        addCustomerResponseEntity = restTemplate.exchange(
                addCustomerUrl,
                HttpMethod.POST,
                new HttpEntity(payload, httpHeaders),
                Map.class
        );
    }

    @Then("the http status code of the add customer response message must be 200 OK")
    public void verifyAddCustomerMessageResponseStatusCode200OK() {
        Assert.assertEquals(HttpStatus.OK, addCustomerResponseEntity.getStatusCode());
    }

    @When("I retrieve the customer {string} {string} using the get customer path")
    public void getCustomer(String firstName, String lastName) {
        String getUrl = String.format("%sfirstName=%s&lastName=%s",getCustomerUrl, firstName, lastName);
        getCustomerResponseEntity = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                new HttpEntity<>("", new HttpHeaders()),
                GetCustomerResponseMessage.class
        );
    }

    @Then("the {string} {string} must be retrieved")
    public void verifyCustomer(String expectedFirstName, String expectedLastName) throws Exception {
        Assert.assertEquals(expectedFirstName, getCustomerResponseEntity.getBody().getFirstName());
        Assert.assertEquals(expectedLastName,  getCustomerResponseEntity.getBody().getLastName());
    }

}
