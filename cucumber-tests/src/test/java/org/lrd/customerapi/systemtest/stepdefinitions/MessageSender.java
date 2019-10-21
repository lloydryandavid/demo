package org.lrd.customerapi.systemtest.stepdefinitions;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.lrd.customerapi.systemtest.model.GetCustomerResponseMessage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;


public class MessageSender extends CustomerApiBaseTestClass {

    @When("I send this message to the add customer path")
    public void addCustomer() {
        responseEntity = restTemplate.exchange(
                addCustomerUrl,
                HttpMethod.POST,
                new HttpEntity(payload, httpHeaders),
                String.class
        );
    }

    @Then("the http status code must be 200 OK")
    public void verifyStatusCode200OK() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @When("I retrieve the customer {string} {string} using the get customer path")
    public void getCustomer(String firstName, String lastName) {
        String getUrl = String.format("%sfirstName=%s&lastName=%s",getCustomerUrl, firstName, lastName);
        responseEntity = restTemplate.exchange(
                getUrl,
                HttpMethod.GET,
                new HttpEntity<>("", new HttpHeaders()),
                String.class
        );
    }

    @Then("the {string} {string} must be retrieved")
    public void verifyCustomer(String expectedFirstName, String expectedLastName) throws Exception {
        GetCustomerResponseMessage customer = new ObjectMapper().readValue(responseEntity.getBody(), new TypeReference<GetCustomerResponseMessage>(){});
        Assert.assertEquals(expectedFirstName, customer.getFirstName());
        Assert.assertEquals(expectedLastName, customer.getLastName());
    }

}
