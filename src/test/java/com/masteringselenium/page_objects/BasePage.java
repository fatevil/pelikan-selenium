package com.masteringselenium.page_objects;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public final void initFields() {
        PageFactory.initElements(driver, this);
    }

    public abstract void waitFor();
}