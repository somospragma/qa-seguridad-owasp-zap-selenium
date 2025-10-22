package org.demo.selenium_zap_pom.pages;

import org.demo.selenium_zap_pom.MenuInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MonitorsPage extends BasePage implements MenuInterface {
    private static final By MONITORS_MENU = By.xpath("/html/body/div[5]/div/div[1]/div/a[4]");

    public MonitorsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void clickMenu() throws InterruptedException {
        WebElement menuMonitors = wait.until(ExpectedConditions.elementToBeClickable(MONITORS_MENU));
        menuMonitors.click();
        waitForPageLoad();
    }

    public void clickMonitorsMenu() throws InterruptedException {
        clickMenu();
    }
}

