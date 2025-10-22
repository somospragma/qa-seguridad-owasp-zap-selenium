package org.demo.seleniumzappom.pages;

import org.demo.seleniumzappom.interfaces.MenuInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PhonesPage extends BasePage implements MenuInterface {
    private static final By PHONES_MENU = By.xpath("/html/body/div[5]/div/div[1]/div/a[2]");

    public PhonesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void clickMenu() throws InterruptedException {
        WebElement menuPhones = wait.until(ExpectedConditions.elementToBeClickable(PHONES_MENU));
        menuPhones.click();
        waitForPageLoad();
    }

    public void clickPhonesMenu() throws InterruptedException {
        clickMenu();
    }
}
