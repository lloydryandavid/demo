package org.lrd.customerapi.systemtest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org.lrd.customerapi.systemtest"},
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"}
)
public class TestRunner {
}
