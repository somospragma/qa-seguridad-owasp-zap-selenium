package org.demo.seleniumzappom.interfaces;

import org.demo.seleniumzappom.pages.LaptopsPage;
import org.demo.seleniumzappom.pages.MonitorsPage;
import org.demo.seleniumzappom.pages.PhonesPage;
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