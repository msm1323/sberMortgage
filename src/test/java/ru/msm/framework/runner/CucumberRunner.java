package ru.msm.framework.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/scripts"},
        glue = {"ru.msm.framework.steps"},
        tags = {"@all"},
        plugin = {"ru.msm.framework.utils.AllureListener"},  //io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        strict = true
)

public class CucumberRunner {

}
