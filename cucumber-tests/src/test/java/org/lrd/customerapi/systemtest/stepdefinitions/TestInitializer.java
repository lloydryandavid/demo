package org.lrd.customerapi.systemtest.stepdefinitions;


import cucumber.api.java.Before;
import org.lrd.customerapi.CustomerApplication;
import org.lrd.customerapi.systemtest.config.CustomerApiTestConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(
        initializers = ConfigFileApplicationContextInitializer.class,
        classes = CustomerApiTestConfiguration.class
)
public class TestInitializer extends CustomerApiBaseTestClass {

    private static boolean isInitialized = false;

    @Before
    public void loadSprintContext() throws Exception {
        startApplication();
        initDatabase();
    }

    private void startApplication() {
        if(!isInitialized)
            CustomerApplication.main(new String[]{});
        isInitialized = true;
    }

    private void initDatabase() {
        jdbcTemplate.execute("DELETE FROM customer;");
    }

}
