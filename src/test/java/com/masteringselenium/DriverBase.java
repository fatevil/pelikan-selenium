package com.masteringselenium;

import com.masteringselenium.config.DriverFactory;
import com.masteringselenium.listeners.ScreenshotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

@Listeners(ScreenshotListener.class)
public class DriverBase {

    private String currentTestName;
    protected RemoteWebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void setTestName(Method method) {
        currentTestName = method.getName();
    }

    public static RemoteWebDriver getDriver() throws MalformedURLException {
        //return new DriverFactory().getDriver();
         //https://www.guru99.com/gecko-marionette-driver-selenium.html
         //download from https://github.com/mozilla/geckodriver/releases
        System.setProperty("webdriver.gecko.driver", "/home/marek/skola/zks/zks-semestralka/geckodriver");
        System.setProperty("webdriver.chrome.driver", "/home/marek/skola/zks/zks-semestralka/src/test/resources/selenium_standalone_binaries/linux/googlechrome/64bit/chromedriver");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.merge(desiredCapabilities);
        options.setHeadless(false);
        return new ChromeDriver(options);
    }

    @AfterClass(alwaysRun = true)
    public void clearCookies() {
        try {
            driver.manage().deleteAllCookies();
        } catch (Exception ignored) {
            System.out.println("Unable to clear cookies, driver object is not viable...");
        }

        System.out.println("quit");
        driver.quit();
    }

    public static void waitForElement(WebDriver driver, final By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) d -> d.findElement(by).isDisplayed());

        /*wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });*/
    }

    protected boolean waitForJSandJQueryToLoad(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                try {
                    return ((Long) ((JavascriptExecutor) d).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return ((JavascriptExecutor) d).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}