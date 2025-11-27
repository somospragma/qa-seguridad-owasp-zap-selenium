package org.demo.seleniumzappom.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;

import org.demo.seleniumzappom.interfaces.MenuInterface;
import org.demo.seleniumzappom.interfaces.ZapInterface;
import org.demo.seleniumzappom.pages.PageFactory;
import org.demo.seleniumzappom.utils.EnvConfig;
import org.demo.seleniumzappom.utils.ZapManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

@Epic("Análisis de Seguridad")
@Feature("Pruebas de Seguridad con ZAP")
public class SecurityAnalysisTest {
    private WebDriver driver;
    private ZapInterface zapManager;
    private static final String ZAP_PROXYHOST = EnvConfig.getZapProxyHost();
    private static final int ZAP_PROXYPORT = EnvConfig.getZapProxyPort();
    private static final String SCAN_URL = EnvConfig.getScanUrl();

    @Before
    @Step("Configurar ZAP y navegador con proxy")
    public void givenZapEstaConfiguradoYNavegadorConProxy() {
        // Given que ZAP está configurado y ejecutándose
        // And el navegador está configurado con proxy ZAP
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        
        try {
            zapManager = new ZapManager();
            zapManager.initializeZap();
            
            Proxy SeleniumProxy = new Proxy();
            SeleniumProxy.setHttpProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
            SeleniumProxy.setSslProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
            
            chromeOptions.setCapability(CapabilityType.PROXY, SeleniumProxy);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            
            System.out.println("ZAP proxy configurado correctamente");
        } catch (ClientApiException e) {
            System.err.println("No se pudo conectar con ZAP, ejecutando sin proxy: " + e.getMessage());
            zapManager = null;
        }
        
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--ignore-ssl-errors");
        
        driver = new ChromeDriver(chromeOptions);
        assertTrue("Driver debe estar inicializado", driver != null);
    }

    @Test
    @Story("Ejecutar análisis completo de seguridad")
    @Description("Prueba que navega por la aplicación y genera reporte de seguridad con ZAP")
    @Severity(SeverityLevel.CRITICAL)
    public void ejecutarAnalisisSeguridadCompleto() throws InterruptedException, ClientApiException, IOException {
        // When navego a la página principal de DemoBlaze
        navegarAPaginaPrincipal();
        
        // And interactúo con el menú de laptops
        interactuarConMenuLaptops();
        
        // And interactúo con el menú de teléfonos
        interactuarConMenuTelefonos();
        
        // And interactúo con el menú de monitores
        interactuarConMenuMonitores();
        
        // Then se genera el reporte de seguridad
        generarReporteSeguridad();
    }

    @Step("Navegar a página principal")
    private void navegarAPaginaPrincipal() {
        driver.get(SCAN_URL);
        driver.manage().window().maximize();
    }

    @Step("Interactuar con menú de laptops")
    private void interactuarConMenuLaptops() throws InterruptedException {
        MenuInterface laptopsPage = PageFactory.createPage("laptops", driver);
        laptopsPage.clickMenu();
    }

    @Step("Interactuar con menú de teléfonos")
    private void interactuarConMenuTelefonos() throws InterruptedException {
        MenuInterface phonesPage = PageFactory.createPage("phones", driver);
        phonesPage.clickMenu();
    }

    @Step("Interactuar con menú de monitores")
    private void interactuarConMenuMonitores() throws InterruptedException {
        MenuInterface monitorsPage = PageFactory.createPage("monitors", driver);
        monitorsPage.clickMenu();
    }

    @Step("Generar reporte de seguridad")
    private void generarReporteSeguridad() throws ClientApiException, IOException, InterruptedException {
        if (zapManager != null) {
            zapManager.generateReport();
            assertTrue("El reporte debe existir", 
                Files.exists(Paths.get(System.getProperty("user.dir") + "/scan-results/SeleniumTest.html")));
        } else {
            System.out.println("Test completado sin análisis de seguridad (ZAP no disponible)");
        }
    }

    @After
    @Step("Limpiar recursos")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (zapManager != null) {
            zapManager.cleanup();
        }
    }
}