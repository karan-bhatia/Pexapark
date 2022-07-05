package com.pexapark.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.ordermacbook.stepdefinition"},
        features = "src/test/resources/features/",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report",
                "json:json_output/cucumber.json",
                "junit:junit_xml/cucumber.xml",
                "pretty:target/cucumber-pretty.txt", "usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"
        }
)
class TestRunner {
}