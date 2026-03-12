package com.sofkianos.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Acceder")
    private WebElement botonAcceder;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void abrirPaginaPrincipal() {
        driver.get("http://localhost:5173");
    }

    public void hacerClickEnAcceder() {
        wait.until(ExpectedConditions.elementToBeClickable(botonAcceder)).click();
    }
}