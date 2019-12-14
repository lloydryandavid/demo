package org.lrd.customerapi.systemtest.init;


import cucumber.api.java.Before;
import org.lrd.customerapi.CustomerApplication;
import org.lrd.customerapi.systemtest.components.CustomerApiTestConfiguration;
import org.lrd.customerapi.systemtest.components.TestConfiguration;
import org.lrd.customerapi.systemtest.testcontext.CucumberTestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ContextHierarchy({
        @ContextConfiguration(
                initializers = ConfigFileApplicationContextInitializer.class,
                classes = {
                        CustomerApiTestConfiguration.class,
                        TestConfiguration.class
                },
                loader = SpringBootContextLoader.class
        ),
        @ContextConfiguration(
                initializers = ConfigFileApplicationContextInitializer.class,
                classes = CustomerApplication.class,
                loader = SpringBootContextLoader.class
        ),
})
public class TestInitializer {

    @Autowired
    private CucumberTestContext testContext;

    @Before
    public void loadSprintContext() throws Exception {
        testContext.jdbcTemplate.execute("DELETE FROM customer;");
    }

}
