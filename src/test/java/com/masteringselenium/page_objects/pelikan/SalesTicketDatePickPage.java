package com.masteringselenium.page_objects.pelikan;

import com.masteringselenium.page_objects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesTicketDatePickPage extends BasePage {

    /**
     * Recognizes the driver and prepares new instance of this class for handling the page.
     *
     * @param driver
     * @return new instance of this class
     */
    public static SalesTicketDatePickPage start(WebDriver driver) {
        SalesTicketDatePickPage homePage = new SalesTicketDatePickPage(driver);
        homePage.waitFor();
        homePage.initFields();
        return homePage;
    }

    private SalesTicketDatePickPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitFor() {

        // wait 30 seconds at most for the sales ticket date selection table to appear
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until((ExpectedCondition<Boolean>) d -> {
            WebElement toDivElement = d.findElement(By.xpath("//div[@id=\"to\" and contains(@class, \"box\")]"));
            WebElement fromDivElement = d.findElement(By.xpath("//div[@id=\"from\" and contains(@class, \"box\") and contains(@class, \"active\")]"));

            return toDivElement.isDisplayed() && fromDivElement.isDisplayed();
        });
    }

    public WebElement getContinueButton() {
        PelikanWebDriverUtils.moveToElement(driver, continueButton);
        return continueButton;
    }

    public WebElement getCorrentStartDateFirstElement() {
        return correntStartDateFirstElement;
    }

    public WebElement getCorrentStartDateLastElement() {
        return correntStartDateLastElement;
    }

    /**
     * Move to the selected elemenet and return it.
     *
     * @return the element
     */
    public WebElement getCorrectToDateElement() {
        PelikanWebDriverUtils.moveToElement(driver, correctToDateElement);
        return correctToDateElement;
    }

    /**
     * Move to the selected elemenet and return it.
     *
     * @return the element
     */
    public WebElement getDisabledToDate() {
        PelikanWebDriverUtils.moveToElement(driver, disabledToDate);
        return disabledToDate;
    }

    public WebElement getInactiveStartDate() {
        return inactiveStartDate;
    }

    public WebElement getPriceElement() {
        return priceElement;
    }

    /**
     * Different structure for multiple browsers and the versions: Firefox, Chrome.
     */
    @FindAll({@FindBy(how = How.XPATH, using = "//div[@id=\"from\"]//a[contains(@class, \"day\") and contains(@class, \"level-1\")][last()]"),
            @FindBy(how = How.XPATH, using = "//div[@id=\"from\"]//a[contains(@class, \"day\") and contains(@class, \"has-price\")][last()]")})
    private WebElement correntStartDateLastElement;

    /**
     * Different structure for multiple browsers and the versions: Firefox, Chrome.
     */
    @FindAll({@FindBy(how = How.XPATH, using = "//div[@id=\"from\"]//a[contains(@class, \"day\") and contains(@class, \"level-1\")][1]"),
            @FindBy(how = How.XPATH, using = "//div[@id=\"from\"]//a[contains(@class, \"day\") and contains(@class, \"has-price\")][1]")})
    private WebElement correntStartDateFirstElement;

    /**
     * Different structure for multiple browsers and the versions: Firefox, Chrome.
     */
    @FindAll({@FindBy(how = How.XPATH, using = "//div[@id=\"to\"]//a[contains(@class, \"day\") and contains(@class, \"level-1\")][last()]"),
            @FindBy(how = How.XPATH, using = "//div[@id=\"to\"]//a[contains(@class, \"day\") and contains(@class, \"has-price\")][last()]")})
    private WebElement correctToDateElement;

    /**
     * Different structure for multiple browsers and the versions: Firefox, Chrome.
     */
    @FindAll({@FindBy(how = How.XPATH, using = "//div[@id=\"to\"]//a[contains(@class, \"day\") and contains(@class, \"level-1\") and contains(@class, \"disabled\")][1]"),
            @FindBy(how = How.XPATH, using = "//div[@id=\"to\"]//a[contains(@class, \"day\") and contains(@class, \"has-price\") and contains(@class, \"disabled\")][1]")})
    private WebElement disabledToDate;

    @FindBy(how = How.XPATH, using = "//div[@id = \"from\"]//a[contains(@class, \"day\") and not(contains(@class, \"level-1\"))]")
    private WebElement inactiveStartDate;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item\")]/span[contains(@class, \"price\")]")
    private WebElement priceElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item\")]/a[contains(@class, \"btn\") and contains(@class, \"continue\")]")
    private WebElement continueButton;
}



