package com.masteringselenium.page_objects.pelikan;

import com.masteringselenium.page_objects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PelikanTicketDateSelectionPage extends BasePage {


    /**
     * Different structure for multiple browsers and the versions: Firefox, Chrome.
     */
    @FindAll({@FindBy(how = How.XPATH, using = "//div[@id=\"from\"]//a[contains(@class, \"day\") and contains(@class, \"level-1\")]"),
            @FindBy(how = How.XPATH, using = "//div[@id=\"from\"]//a[contains(@class, \"day\") and contains(@class, \"has-price\")]")})
    private WebElement correntStartDate;

    @FindBy(how = How.XPATH, using = "//div[@id = \"from\"]//a[contains(@class, \"day\") and not(contains(@class, \"level-1\"))]")
    private WebElement inactiveStartDate;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item\")]/span[contains(@class, \"price\")]")
    private WebElement priceElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item\")]/a[contains(@class, \"btn\") and contains(@class, \"continue\")]")
    private WebElement continueButton;

    /**
     * Recognizes the driver and prepares new instance of this class for handling the page.
     *
     * @param driver
     * @return new instance of this class
     */
    public static PelikanTicketDateSelectionPage start(WebDriver driver) {
        PelikanTicketDateSelectionPage homePage = new PelikanTicketDateSelectionPage(driver);
        homePage.waitFor();
        homePage.initFields();
        return homePage;
    }

    private PelikanTicketDateSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitFor() {

        // wait 10 seconds at most for the sales ticket date selection table to appear
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until((ExpectedCondition<Boolean>) d -> {
            WebElement toDivElement = d.findElement(By.xpath("//div[@id=\"to\" and contains(@class, \"box\")]"));
            WebElement fromDivElement = d.findElement(By.xpath("//div[@id=\"from\" and contains(@class, \"box\") and contains(@class, \"active\")]"));

            return toDivElement.isDisplayed() && fromDivElement.isDisplayed();
        });
    }

    public WebElement getCorrentStartDate() {
        return correntStartDate;
    }

    public WebElement getInactiveStartDate() {
        return inactiveStartDate;
    }

    public WebElement getPriceElement() {
        return priceElement;
    }
}



