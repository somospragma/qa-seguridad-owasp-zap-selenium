package org.demo.selenium_zap_pom.pages;

import org.demo.selenium_zap_pom.MenuInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LaptopsPage extends BasePage implements MenuInterface {
    private static final By LAPTOPS_MENU = By.xpath("/html/body/div[5]/div/div[1]/div/a[3]");

    public LaptopsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void clickMenu() throws InterruptedException {
        WebElement menuLaptop = wait.until(ExpectedConditions.elementToBeClickable(LAPTOPS_MENU));
        menuLaptop.click();
        waitForPageLoad();
    }

    public void clickLaptopsMenu() throws InterruptedException {
        clickMenu();
    }
}

