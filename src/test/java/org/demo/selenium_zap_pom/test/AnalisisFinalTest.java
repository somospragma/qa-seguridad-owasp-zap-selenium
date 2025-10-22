package org.demo.selenium_zap_pom.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.demo.selenium_zap_pom.MenuInterface;
import org.demo.selenium_zap_pom.PageFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.demo.selenium_zap_pom.ZapInterface;
import org.demo.selenium_zap_pom.ZapManager;
import org.zaproxy.clientapi.core.ClientApiException;
import io.qameta.allure.*;

import java.io.IOException;

@Epic("Análisis de Seguridad")
@Feature("Pruebas de Seguridad con ZAP - Versión Original")
public class AnalisisFinalTest {
    private WebDriver driver;
    private ZapInterface zapManager;

    private static final String ZAP_PROXYHOST = "localhost";
    private static final int ZAP_PROXYPORT = 8080;
    private static final String SCAN_URL = "https://www.demoblaze.com/";

    @Before
    @Step("Configurar entorno de prueba")
    public void setup() {
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
        } catch (Exception e) {
            System.err.println("Error general en configuración: " + e.getMessage());
            zapManager = null;
        }
        
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--ignore-ssl-errors");
        
        driver = new ChromeDriver(chromeOptions);
        driver.get(SCAN_URL);
        driver.manage().window().maximize();
    }

    @Test
    @Story("Ejecutar pruebas de navegación y seguridad")
    @Description("Prueba original que navega por menús y genera reporte ZAP")
    @Severity(SeverityLevel.CRITICAL)
    public void execTests() throws InterruptedException, ClientApiException, IOException {
        // Usar factory para crear páginas con interfaces
        MenuInterface laptopsPage = PageFactory.createPage("laptops", driver);
        MenuInterface phonesPage = PageFactory.createPage("phones", driver);
        MenuInterface monitorsPage = PageFactory.createPage("monitors", driver);

        // Interacción con los menús usando interfaces
        laptopsPage.clickMenu();
        phonesPage.clickMenu();
        monitorsPage.clickMenu();

        // Generar informe solo si ZAP está disponible
        if (zapManager != null) {
            zapManager.generateReport();
        } else {
            System.out.println("Test completado sin análisis de seguridad (ZAP no disponible)");
        }
    }

    @After
    @Step("Limpiar recursos de prueba")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (zapManager != null) {
            zapManager.cleanup();
        }
    }
}