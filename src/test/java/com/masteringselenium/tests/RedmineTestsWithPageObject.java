package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.redmine.LoginPage;
import com.masteringselenium.page_objects.redmine.ProjectsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RedmineTestsWithPageObject extends DriverBase {

    @Test
    public void step1_loginTest() {
        LoginPage loginPage = LoginPage.goTo("http://demo.redmine.org", driver);

        loginPage
                .enterUserName("testuser1358")
                .enterPassword("testit135")
                .login();
    }

    @Test(dependsOnMethods= "step1_loginTest")
    public void step2_projectTest() {
        LoginPage loginPage = new LoginPage(driver);
        ProjectsPage projectsPage = loginPage.goToProjects();
        projectsPage.waitFor();

        // FIND TESTING PROJECT
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
