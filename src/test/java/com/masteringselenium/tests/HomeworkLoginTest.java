package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.redmine.LoginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class HomeworkLoginTest extends DriverBase {

    private static final String USERNAME = "kozloma2";
    private static final String PASSWORD = "3U39WzVUDcDvYXf";
    private static final String WRONG_PASSWORD = "password";

    @Test
    public void loginTest_successful() {
        LoginPage loginPage = LoginPage.goTo("http://demo.redmine.org", driver);

        loginPage
                .enterUserName(USERNAME)
                .enterPassword(PASSWORD)
                .login();

        // assert that the page does not contain login button anymore
        Throwable thrown = catchThrowable(() -> { driver.findElementById("login"); });
        assertThat(thrown).isInstanceOf(org.openqa.selenium.NoSuchElementException.class);

        // asert that the name of the logged in user is the same as given username
        WebElement loggedAsElement = driver.findElementByXPath("//div[@id = 'loggedas']/a[contains(@class, 'user') and contains(@class, 'active')]");
        assertThat(loggedAsElement.getText()).isEqualTo(USERNAME);
    }


    @Test
    public void loginTest_unsuccessful_wrongPassword() {
        LoginPage loginPage = LoginPage.goTo("http://demo.redmine.org", driver);

        loginPage
                .enterUserName(USERNAME)
                .enterPassword(WRONG_PASSWORD)
                .login();

        // assert that the page still does contain login button
        List<WebElement> loginButton = driver.findElementsByXPath("//input[@name = 'login']");
        assertThat(loginButton.size()).isNotEqualTo(0);

        // asert that an error flash message appeared on the page
        List<WebElement> loggedAsElements = driver.findElementsByXPath("//div[@id = 'content']/div[@id = 'flash_error']");
        assertThat(loggedAsElements.size()).isNotEqualTo(0);
    }

    @Test
    public void loginTest_unsuccessful_emptyPassword() {
        LoginPage loginPage = LoginPage.goTo("http://demo.redmine.org", driver);

        loginPage
                .enterUserName(USERNAME)
                .enterPassword("")
                .login();

        // assert that the page still does contain login button
        List<WebElement> loginButton = driver.findElementsByXPath("//input[@name = 'login']");
        assertThat(loginButton.size()).isNotEqualTo(0);

        // asert that an error flash message appeared on the page
        List<WebElement> loggedAsElements = driver.findElementsByXPath("//div[@id = 'content']/div[@id = 'flash_error']");
        assertThat(loggedAsElements.size()).isNotEqualTo(0);
    }
}
