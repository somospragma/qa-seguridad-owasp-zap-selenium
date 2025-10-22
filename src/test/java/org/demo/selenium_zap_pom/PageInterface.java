package org.demo.selenium_zap_pom;

import org.openqa.selenium.WebDriver;

public interface PageInterface {
    WebDriver getDriver();
    void waitForPageLoad();
}