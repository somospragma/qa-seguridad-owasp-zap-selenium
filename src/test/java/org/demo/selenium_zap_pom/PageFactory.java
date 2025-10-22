package org.demo.selenium_zap_pom;

import org.demo.selenium_zap_pom.pages.LaptopsPage;
import org.demo.selenium_zap_pom.pages.MonitorsPage;
import org.demo.selenium_zap_pom.pages.PhonesPage;
import org.openqa.selenium.WebDriver;

public class PageFactory {
    
    public static MenuInterface createPage(String pageType, WebDriver driver) {
        switch (pageType.toLowerCase()) {
            case "laptops":
                return new LaptopsPage(driver);
            case "phones":
                return new PhonesPage(driver);
            case "monitors":
                return new MonitorsPage(driver);
            default:
                throw new IllegalArgumentException("Tipo de página no válido: " + pageType);
        }
    }
}