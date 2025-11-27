package org.demo.seleniumzappom.pages;

import org.demo.seleniumzappom.interfaces.MenuInterface;
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