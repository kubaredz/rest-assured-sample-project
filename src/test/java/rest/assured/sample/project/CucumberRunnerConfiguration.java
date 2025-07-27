package rest.assured.sample.project;

import org.junit.platform.suite.api.IncludeEngines;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cucumber.junit.platform.engine.Constants.*;

@IncludeEngines("cucumber")
@SelectClasspathResource("rest/assured/sample/project")
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty"
        + ", junit:target/cucumber-reports/Cucumber.xml"
        + ", json:target/cucumber-reports/Cucumber.json"
        + ", io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "rest.assured.sample.project")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME, value = "true")
public class CucumberRunnerConfiguration {
}