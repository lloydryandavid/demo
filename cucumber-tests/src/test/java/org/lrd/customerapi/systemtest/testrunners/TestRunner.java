package org.lrd.customerapi.systemtest.testrunners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {
                "classpath:org.lrd.customerapi.systemtest.init",
                "classpath:org.lrd.customerapi.systemtest.stepdefinitions"
        },
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
        features = {"src/test/resources/org/lrd/customerapi/systemtest/features/"},
        tags = {"@local_test"}
)
public class TestRunner {
}