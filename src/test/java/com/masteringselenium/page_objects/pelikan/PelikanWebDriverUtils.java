package com.masteringselenium.page_objects.pelikan;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PelikanWebDriverUtils {

    public static void moveToElement(WebDriver driver, WebElement element) {
        // move to element
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }
}
