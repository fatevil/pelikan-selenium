package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class RedmineTests extends DriverBase {

    @Test
    public void step1_loginTest() {
        driver.get("http://demo.redmine.org");
        driver.findElement(By.linkText("Sign in")).click();

        waitForElement(driver, By.id("username"));

        driver.findElement(By.id("username"))
                .sendKeys("testuser1358");
        driver.findElement(By.id("password"))
                .sendKeys("testit135");
        driver.findElement(By.name("login")).click();
    }


    @Test(dependsOnMethods= "step1_loginTest")
    public void step2_projectTest() {
        // FIND TESTING PROJECT
        driver.findElement(By.linkText("Projects")).click();
        driver.findElement(By.linkText("TestingProjectTraining")).click();

        // CHECK NUMBER OF BUGS
        WebElement bugsLinkElement = driver.findElement(By.linkText("Bug"));
        WebElement bugsStatsElement = bugsLinkElement.findElement(By.xpath("parent::node()"));
        String bugsStatusString = bugsStatsElement.getText();
        String totalBugsCount = bugsStatusString.substring(bugsStatusString.indexOf("/") + 1).trim();
        int totalBugsCountNumber = (new Integer(totalBugsCount)).intValue();

        Assert.assertTrue(totalBugsCountNumber > 20,"Log message: totalBugsCountNumber is less than 20");
    }

    @Test(dependsOnMethods= "step2_projectTest")
    public void step3_logoutTest() {
        driver.findElement(By.linkText("Sign out")).click();
    }
}
