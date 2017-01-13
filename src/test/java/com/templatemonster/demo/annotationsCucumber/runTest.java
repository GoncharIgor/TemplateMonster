package com.templatemonster.demo.annotationsCucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//TODO implement cucumber testNg runner, instead of provided below
@CucumberOptions(features = "./src/test/java/com/templatemonster/demo/annotationsCucumber/annotation.feature", format = { "pretty",
        "html:target/site/cucumber-pretty",
        "rerun:target/rerun.txt",
        "json:target/cucumber1.json" })
public class runTest extends AbstractTestNGCucumberTests {
}
