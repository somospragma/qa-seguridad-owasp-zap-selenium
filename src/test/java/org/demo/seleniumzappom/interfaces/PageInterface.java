package org.demo.seleniumzappom.interfaces;

import org.openqa.selenium.WebDriver;

public interface PageInterface {
    WebDriver getDriver();
    void waitForPageLoad();
}