package org.lrd.customerapi.systemtest.stepdefinitions;


import org.lrd.customerapi.systemtest.model.GetCustomerResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import java.util.Map;


public class CustomerApiBaseTestClass {

    @Autowired
    protected String addCustomerUrl;

    @Autowired
    protected String getCustomerUrl;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    protected static String payload;

    protected static HttpHeaders httpHeaders;

    protected static ResponseEntity<Map> addCustomerResponseEntity;

    protected static ResponseEntity<GetCustomerResponseMessage> getCustomerResponseEntity;

}
