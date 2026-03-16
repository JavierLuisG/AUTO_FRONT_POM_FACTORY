package com.sofkianos.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KudosPage {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//button[contains(.,'Explorar Kudos')]")
    private WebElement botonExplorarKudos;

    @FindBy(xpath = "//input[@aria-label='Buscar kudos']")
    private WebElement inputBusqueda;

    @FindBy(xpath = "//button[normalize-space()='Aplicar Filtros']")
    private WebElement botonAplicarFiltros;

    @FindBy(xpath = "(//tbody/tr)[1]")
    private List<WebElement> filasKudos;

    public KudosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    public void abrirSeccionDeKudos() {
        wait.until(ExpectedConditions.elementToBeClickable(botonExplorarKudos)).click();
        esperarResultados();
    }

    public void buscarPorMensaje(String mensaje) {
        wait.until(ExpectedConditions.attributeToBe(inputBusqueda, "disabled", ""));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputBusqueda));
        input.clear();
        input.sendKeys(mensaje);
        wait.until(ExpectedConditions.elementToBeClickable(botonAplicarFiltros)).click();
    }

    public void verificarKudoFiltrado(String mensaje) {
        buscarPorMensaje(mensaje);
        esperarResultados();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//table//tr")));
        List<WebElement> filas = driver.findElements(
                org.openqa.selenium.By.xpath("//table//tr"));
        WebElement fila = filasKudos.stream()
                .filter(WebElement::isDisplayed)
                .filter(row -> normalizarTexto(row.getText()).contains(normalizarTexto(mensaje)))
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("No se encontró un Kudo filtrado con el mensaje: " + mensaje)
                );

        assertThat(normalizarTexto(fila.getText())).contains(normalizarTexto(mensaje));
    }

    private void esperarResultados() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.xpath("//table//tr")));
    }

    private String normalizarTexto(String valor) {
        return valor == null
                ? ""
                : valor.trim()
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }
}