package com.sofkianos.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/reconocimiento_kudos.feature",
        glue = "com.sofkianos.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class ReconocimientoRunner {
}