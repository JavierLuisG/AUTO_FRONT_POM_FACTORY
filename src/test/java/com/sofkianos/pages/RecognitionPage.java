package com.sofkianos.pages;

import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RecognitionPage {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    @FindBy(name = "from")
    private WebElement selectorRemitente;

    @FindBy(name = "to")
    private WebElement selectorDestinatario;

    @FindBy(name = "category")
    private WebElement selectorCategoria;

    @FindBy(name = "message")
    private WebElement campoMensaje;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/section/div[3]/div/div[5]/div/div[1]")
    private WebElement controlDeslizanteEnvio;

    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div/div[2]/section/div[3]/div/div[5]/div/div[3]")
    private WebElement ovaloDeEnvio;

    public RecognitionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void seleccionarRemitente(String remitente) {
        seleccionarOpcion(selectorRemitente, remitente);
    }

    public void seleccionarDestinatario(String destinatario) {
        seleccionarOpcion(selectorDestinatario, destinatario);
    }

    public void seleccionarCategoria(String categoria) {
        seleccionarOpcion(selectorCategoria, categoria);
    }

    public void ingresarMensajeDeFelicitacion(String mensaje) {
        WebElement campoEditable = wait.until(ExpectedConditions.visibilityOf(campoMensaje));
        if (esCampoDeTexto(campoEditable)) {
            campoEditable.clear();
            campoEditable.sendKeys(mensaje);
            return;
        }

        campoEditable.click();
        campoEditable.sendKeys(Keys.chord(Keys.CONTROL, "a"), mensaje);
    }

    public void enviarReconocimiento() {
        WebElement control = wait.until(ExpectedConditions.visibilityOf(controlDeslizanteEnvio));
        WebElement ovalo = wait.until(ExpectedConditions.elementToBeClickable(ovaloDeEnvio));
        int desplazamientoHorizontal = Math.max(control.getRect().getWidth() - ovalo.getRect().getWidth() - 8, 40);
        actions.clickAndHold(ovalo)
            .moveByOffset(desplazamientoHorizontal, 0)
            .release()
            .perform();
    }

    private void seleccionarOpcion(WebElement elemento, String valor) {
        WebElement campo = wait.until(ExpectedConditions.elementToBeClickable(elemento));
        String tagName = campo.getTagName();

        if ("select".equalsIgnoreCase(tagName)) {
            new Select(campo).selectByVisibleText(valor);
            return;
        }

        campo.click();

        if (esCampoDeTexto(campo)) {
            campo.clear();
            campo.sendKeys(valor);
        }

        if (seleccionarOpcionVisible(valor)) {
            return;
        }

        campo.sendKeys(Keys.ENTER);
    }

    private boolean seleccionarOpcionVisible(String valor) {
        try {
            WebElement opcion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(construirXpathDeOpcion(valor))));
            opcion.click();
            return true;
        } catch (TimeoutException | NoSuchElementException ignored) {
            return false;
        }
    }

    private boolean esCampoDeTexto(WebElement elemento) {
        String tagName = elemento.getTagName();
        return Objects.equals("input", tagName) || Objects.equals("textarea", tagName);
    }

    private String construirXpathDeOpcion(String valor) {
        String textoEscapado = escaparTextoXpath(valor);
        return "//*[self::li or self::div or self::span or self::button or self::mat-option or self::option][normalize-space()=" + textoEscapado + "]";
    }

    private String escaparTextoXpath(String valor) {
        if (!valor.contains("'")) {
            return "'" + valor + "'";
        }

        String[] segmentos = valor.split("'");
        StringBuilder expresion = new StringBuilder("concat(");

        for (int index = 0; index < segmentos.length; index++) {
            if (index > 0) {
                expresion.append(", \"'\", ");
            }
            expresion.append("'").append(segmentos[index]).append("'");
        }

        expresion.append(")");
        return expresion.toString();
    }
}