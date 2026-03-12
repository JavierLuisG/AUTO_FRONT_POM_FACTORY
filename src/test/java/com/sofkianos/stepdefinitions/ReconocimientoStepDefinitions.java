package com.sofkianos.stepdefinitions;

import com.sofkianos.pages.HomePage;
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

    private HomePage homePage;
    private RecognitionPage recognitionPage;
    private KudosPage kudosPage;

    private String remitente;
    private String destinatario;
    private String categoria;
    private String mensaje;

    @Given("que el usuario ingresa a la pagina de generacion de reconocimientos")
    public void queElUsuarioIngresaALaPaginaDeGeneracionDeReconocimientos() {
        inicializarPaginas();
        homePage.abrirPaginaPrincipal();
        homePage.hacerClickEnAcceder();
    }

    @When("crea un reconocimiento seleccionando el remitente {string}")
    public void seleccionarElRemitente(String remitente) {
        this.remitente = remitente;
        recognitionPage.seleccionarRemitente(remitente);
    }

    @And("selecciona al destinatario {string}")
    public void seleccionarAlDestinatario(String destinatario) {
        this.destinatario = destinatario;
        recognitionPage.seleccionarDestinatario(destinatario);
    }

    @And("selecciona la categoria {string}")
    public void seleccionaLaCategoria(String categoria) {
        this.categoria = categoria;
        recognitionPage.seleccionarCategoria(categoria);
    }

    @And("escribe un mensaje de felicitacion {string}")
    public void escribeUnMensajeDeFelicitacion(String mensaje) {
        this.mensaje = mensaje;
        recognitionPage.ingresarMensajeDeFelicitacion(mensaje);
    }

    @And("envia el reconocimiento")
    public void enviaElReconocimiento() {
        recognitionPage.enviarReconocimiento();
    }

    @Then("explora la seccion de Kudos y verifica que el reconocimiento fue creado")
    public void exploraLaSeccionDeKudosYVerificaQueElReconocimientoFueCreado() {
        kudosPage.abrirSeccionDeKudos();
        kudosPage.verificarKudoFiltrado(mensaje);
    }

    private void inicializarPaginas() {
        if (recognitionPage != null && kudosPage != null && homePage != null) return;

        WebDriver driver = ThucydidesWebDriverSupport.getDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
        recognitionPage = PageFactory.initElements(driver, RecognitionPage.class);
        kudosPage = PageFactory.initElements(driver, KudosPage.class);
    }
}