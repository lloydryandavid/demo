package org.lrd.customerapi.systemtest.testcontext;

import org.lrd.customerapi.systemtest.model.GetCustomerResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class CucumberTestContext {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    public String payload;

    public HttpHeaders httpHeaders;

    public ResponseEntity<Map> addCustomerResponseEntity;

    public ResponseEntity<GetCustomerResponseMessage> getCustomerResponseEntity;

}
