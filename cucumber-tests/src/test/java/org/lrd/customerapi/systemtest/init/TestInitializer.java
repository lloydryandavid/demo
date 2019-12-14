package org.lrd.customerapi.systemtest.stepdefinitions;


import cucumber.api.java.Before;
import org.lrd.customerapi.systemtest.components.CustomerApiTestConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ContextConfiguration(
        initializers = ConfigFileApplicationContextInitializer.class,
        classes = CustomerApiTestConfiguration.class,
        loader = SpringBootContextLoader.class
)
public class TestInitializer extends CustomerApiBaseTestClass {

    private static boolean isInitialized = false;

    @Before
    public void loadSprintContext() throws Exception {
        testContext.jdbcTemplate.execute("DELETE FROM customer;");
    }

}
