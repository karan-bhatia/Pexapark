package com.pexapark.stepdefinition;

import com.pexapark.util.Generic;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class Hooks extends Generic {

    @Before
    public void init(Scenario scenario) {
        this.scenario = scenario;
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                takeScreenShot();
            }
            if (driver != null) {
                driver.close();
                driver.quit();
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
