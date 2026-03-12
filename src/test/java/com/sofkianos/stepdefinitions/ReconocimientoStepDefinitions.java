package com.sofkianos.stepdefinitions;

import com.sofkianos.pages.KudosPage;
import com.sofkianos.pages.RecognitionPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.webdriver.ThucydidesWebDriverSupport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ReconocimientoStepDefinitions {

    private RecognitionPage recognitionPage;
    private KudosPage kudosPage;

    private String remitente;
    private String destinatario;
    private String categoria;
    private String mensaje;

    @Given("que el usuario ingresa a la pagina de generacion de reconocimientos")
    public void queElUsuarioIngresaALaPaginaDeGeneracionDeReconocimientos() {
        inicializarPaginas();
        recognitionPage.abrirPaginaDeReconocimientos();
    }

    @When("crea un reconocimiento seleccionando el {string}")
    public void creaUnReconocimientoSeleccionandoEl(String remitente) {
        inicializarPaginas();
        this.remitente = remitente;
        recognitionPage.seleccionarRemitente(remitente);
    }

    @And("selecciona al {string}")
    public void seleccionaAl(String destinatario) {
        inicializarPaginas();
        this.destinatario = destinatario;
        recognitionPage.seleccionarDestinatario(destinatario);
    }

    @And("selecciona la categoria {string}")
    public void seleccionaLaCategoria(String categoria) {
        inicializarPaginas();
        this.categoria = categoria;
        recognitionPage.seleccionarCategoria(categoria);
    }

    @And("escribe un mensaje de felicitacion {string}")
    public void escribeUnMensajeDeFelicitacion(String mensaje) {
        inicializarPaginas();
        this.mensaje = mensaje;
        recognitionPage.ingresarMensajeDeFelicitacion(mensaje);
    }

    @And("envia el reconocimiento")
    public void enviaElReconocimiento() {
        inicializarPaginas();
        recognitionPage.enviarReconocimiento();
    }

    @Then("explora la seccion de Kudos y verifica que el reconocimiento fue creado")
    public void exploraLaSeccionDeKudosYVerificaQueElReconocimientoFueCreado() {
        inicializarPaginas();
        kudosPage.abrirSeccionDeKudos();
        kudosPage.verificarReconocimientoCreado(remitente, destinatario, categoria, mensaje);
    }

    private void inicializarPaginas() {
        WebDriver driver = ThucydidesWebDriverSupport.getDriver();

        if (recognitionPage == null) {
            recognitionPage = PageFactory.initElements(driver, RecognitionPage.class);
        }

        if (kudosPage == null) {
            kudosPage = PageFactory.initElements(driver, KudosPage.class);
        }
    }
}