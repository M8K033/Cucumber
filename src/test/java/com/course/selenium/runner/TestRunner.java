package com.course.selenium.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/course/selenium/features",
        glue = {"com.course.selenium.stepdefinitions", "com.course.selenium.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/report.html"},
        tags = "(@done or @ui) and not @bug"
)
public class TestRunner {
}
