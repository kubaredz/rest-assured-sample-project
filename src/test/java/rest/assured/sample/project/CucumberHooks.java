package rest.assured.sample.project;

import org.slf4j.Logger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.LoggerFactory;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private static final Logger logger = LoggerFactory.getLogger(CucumberHooks.class);

    @Before
    public void before(final Scenario scenario) {
        logger.info("Scenario: {} has started", scenario.getName());
    }

    @After
    public void after(final Scenario scenario) {
        logger.info("Scenario: {} has finished", scenario.getName());
    }
}