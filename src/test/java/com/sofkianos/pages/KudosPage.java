package com.sofkianos.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.openqa.selenium.TimeoutException;
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

    @FindBy(xpath = "//a[normalize-space()='Kudos'] | //button[normalize-space()='Kudos'] | //*[@role='tab' and contains(translate(normalize-space(.),'KUDOS','kudos'),'kudos')] | //*[(self::a or self::button) and contains(translate(normalize-space(.),'KUDOS','kudos'),'kudos')]")
    private List<WebElement> accesosAKudos;

    @FindBy(xpath = "//*[self::section or self::div][contains(@class,'kudos') or contains(@class,'kudo') or contains(@class,'recognition') or contains(@data-testid,'kudos') or contains(@id,'kudos')]")
    private List<WebElement> contenedoresDeKudos;

    @FindBy(xpath = "//*[self::article or self::li or self::div][contains(@class,'kudo') or contains(@class,'recognition') or contains(@data-testid,'kudo') or contains(@data-testid,'recognition')]")
    private List<WebElement> tarjetasDeReconocimiento;

    public KudosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    public void abrirSeccionDeKudos() {
        if (seccionKudosDisponible()) {
            return;
        }

        if (abrirDesdeNavegacion()) {
            esperarContenidoDeKudos();
            return;
        }

        driver.get(obtenerUrlDeKudos());
        esperarContenidoDeKudos();
    }

    public void verificarReconocimientoCreado(String remitente, String destinatario, String categoria, String mensaje) {
        esperarContenidoDeKudos();

        WebElement reconocimiento = tarjetasDeReconocimiento.stream()
            .filter(WebElement::isDisplayed)
            .filter(tarjeta -> contieneDatosDelReconocimiento(tarjeta, remitente, destinatario, categoria, mensaje))
            .findFirst()
            .orElseThrow(() -> new AssertionError("No se encontro un Kudo con los datos esperados."));

        String contenido = normalizarTexto(reconocimiento.getText());
        assertThat(contenido).contains(normalizarTexto(remitente));
        assertThat(contenido).contains(normalizarTexto(destinatario));
        assertThat(contenido).contains(normalizarTexto(categoria));
        assertThat(contenido).contains(normalizarTexto(mensaje));
    }

    private boolean abrirDesdeNavegacion() {
        return accesosAKudos.stream()
            .filter(WebElement::isDisplayed)
            .findFirst()
            .map(acceso -> {
                wait.until(ExpectedConditions.elementToBeClickable(acceso)).click();
                return true;
            })
            .orElse(false);
    }

    private boolean seccionKudosDisponible() {
        try {
            return contenedoresDeKudos.stream().anyMatch(WebElement::isDisplayed)
                || tarjetasDeReconocimiento.stream().anyMatch(WebElement::isDisplayed);
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    private void esperarContenidoDeKudos() {
        wait.until(driver -> contenedoresDeKudos.stream().anyMatch(WebElement::isDisplayed)
            || tarjetasDeReconocimiento.stream().anyMatch(WebElement::isDisplayed));
    }

    private boolean contieneDatosDelReconocimiento(WebElement tarjeta, String remitente, String destinatario, String categoria, String mensaje) {
        String contenido = normalizarTexto(tarjeta.getText());
        return contenido.contains(normalizarTexto(remitente))
            && contenido.contains(normalizarTexto(destinatario))
            && contenido.contains(normalizarTexto(categoria))
            && contenido.contains(normalizarTexto(mensaje));
    }

    private String obtenerUrlDeKudos() {
        return Optional.ofNullable(System.getProperty("sofkianos.kudos.url"))
            .filter(url -> !url.isBlank())
            .or(() -> Optional.ofNullable(System.getenv("SOFKIANOS_KUDOS_URL")).filter(url -> !url.isBlank()))
            .or(() -> Optional.ofNullable(System.getProperty("webdriver.base.url")).filter(url -> !url.isBlank()).map(this::anexarRutaKudos))
            .or(() -> Optional.ofNullable(System.getenv("SOFKIANOS_BASE_URL")).filter(url -> !url.isBlank()).map(this::anexarRutaKudos))
            .orElseGet(this::obtenerUrlDeKudosDesdePaginaActual);
    }

    private String obtenerUrlDeKudosDesdePaginaActual() {
        String urlActual = driver.getCurrentUrl();
        if (urlActual == null || urlActual.isBlank()) {
            throw new IllegalStateException("No se encontro la URL de Kudos. Configure sofkianos.kudos.url o webdriver.base.url.");
        }
        return anexarRutaKudos(urlActual);
    }

    private String anexarRutaKudos(String baseUrl) {
        try {
            URI uri = new URI(baseUrl);
            String ruta = uri.getPath() == null ? "" : uri.getPath();
            String rutaBase = ruta.endsWith("/") ? ruta.substring(0, ruta.length() - 1) : ruta;
            String rutaKudos = rutaBase.endsWith("/kudos") ? rutaBase : rutaBase + "/kudos";
            return new URI(uri.getScheme(), uri.getAuthority(), rutaKudos, null, null).toString();
        } catch (URISyntaxException exception) {
            if (baseUrl.endsWith("/kudos")) {
                return baseUrl;
            }
            return baseUrl.endsWith("/") ? baseUrl + "kudos" : baseUrl + "/kudos";
        }
    }

    private String normalizarTexto(String valor) {
        return valor == null ? "" : valor.trim().replaceAll("\\s+", " ").toLowerCase(Locale.ROOT);
    }
}