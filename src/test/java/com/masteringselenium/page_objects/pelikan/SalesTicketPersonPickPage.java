package com.masteringselenium.page_objects.pelikan;

import com.masteringselenium.page_objects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SalesTicketPersonPickPage extends BasePage {

    /**
     * Recognizes the driver and prepares new instance of this class for handling the page.
     *
     * @param driver
     * @return new instance of this class
     */
    public static SalesTicketPersonPickPage start(WebDriver driver) {
        SalesTicketPersonPickPage page = new SalesTicketPersonPickPage(driver);
        page.waitFor();
        page.initFields();
        return page;
    }

    private SalesTicketPersonPickPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitFor() {

        // wait 30 seconds at most for the sales ticket date selection table to appear
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until((ExpectedCondition<Boolean>) d -> {
            WebElement toDivElement = d.findElement(By.xpath("//div[@id=\"to\" and contains(@class, \"box\")]"));
            WebElement fromDivElement = d.findElement(By.xpath("//div[@id=\"from\" and contains(@class, \"box\")]"));

            return toDivElement.isDisplayed() && fromDivElement.isDisplayed();
        });
    }

    public WebElement getOpenPersonsSelectionElement() {
        return openPersonsSelectionElement;
    }

    /**
     * Gets the price from textual valuse.
     * <p>
     * Example inputs: 1 300 Kč, 123 Kč
     *
     * @return
     */
    public int getPrice() {
        String priceElementText = priceElement.getText();
        String[] strings = priceElementText.split(" ");

        int price;
        if (strings.length == 3) {
            price = Integer.valueOf(strings[0]) * 1000 + Integer.valueOf(strings[1]);
        } else {
            price = Integer.valueOf(strings[0]);
        }
        return price;
    }


    /**
     * Return xpath selector for person inputs.
     * <p>
     * Possibly use with index 1-4 and class <i>left, right, info</i>.
     *
     * @return xpath selector string
     */
    public static String inputXpathBuilder(int index, String clazz) {
        String xpath = "//div[contains(@class, \"dropdown-tickets\")]//li[INDEX]//*[contains(@class, \"CLASS\")]";
        xpath = xpath.replace("CLASS", clazz);
        xpath = xpath.replace("INDEX", String.valueOf(index));
        return xpath;
    }

    /**
     * Some browser dont display the youth person option. Return the next option instead.
     *
     * @return toddler right input
     */
    public WebElement getToddlerIncrementElement() {
        try {
            toddlerIncrementElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return youthIncrementElement;
        }
        return toddlerIncrementElement;
    }

    /**
     * Some browser dont display the youth person option. Return the next option instead.
     *
     * @return toddler left input
     */
    public WebElement getToddlerDecrementElement() {
        PelikanWebDriverUtils.moveToElement(driver, adultDecrementElement);
        try {
            toddlerDecrementElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return youthDecrementElement;
        }
        return toddlerDecrementElement;
    }

    /**
     * Some browser dont display the youth person option. Return the next option instead.
     *
     * @return toddler info label
     */
    public WebElement getToddlerValueElement() {
        try {
            toddlerIncrementElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return youthValueElement;
        }
        return toddlerValueElement;
    }

    public boolean isContinueButtonDisabled() {
        String disabledAttribute = getContinueButton().getAttribute("disabled");
        if (disabledAttribute == null) {
            return false;
        } else {
            return disabledAttribute.equals("true") || disabledAttribute.equals("disabled");
        }
    }

    public WebElement getAdultDecrementElement() {
        PelikanWebDriverUtils.moveToElement(driver, adultDecrementElement);
        return adultDecrementElement;
    }

    public WebElement getAdultValueElement() {
        return adultValueElement;
    }

    public WebElement getYouthIncrementElement() {
        return youthIncrementElement;
    }

    public WebElement getYouthDecrementElement() {
        PelikanWebDriverUtils.moveToElement(driver, adultDecrementElement);
        return youthDecrementElement;
    }

    public WebElement getYouthValueElement() {
        return youthValueElement;
    }

    public WebElement getChildIncrementElement() {
        return childIncrementElement;
    }

    public WebElement getChildDecrementElement() {
        PelikanWebDriverUtils.moveToElement(driver, adultDecrementElement);
        return childDecrementElement;
    }

    public WebElement getChildValueElement() {
        return childValueElement;
    }

    public WebElement getAdultIncrementElement() {
        return adultIncrementElement;
    }

    public WebElement getNumberOfPersonsValidation() {
        return numberOfPersonsValidation;
    }

    public WebElement getContinueButton() {
        PelikanWebDriverUtils.moveToElement(driver, adultDecrementElement);
        return continueButton;
    }

    public WebElement getPriceElement() {
        return priceElement;
    }

    @FindAll({
            @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item who\")]//div[contains(@class, \"form-control\") and contains(@class, \"open\")]/div[contains(@class, \"input\")]"),
            @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item who\")]//div[contains(@class, \"form-control\") and contains(@class, \"with-icon\")]/div[contains(@class, \"input\")]")
    })
    private WebElement openPersonsSelectionElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[1]//a[contains(@class, \"right\")]")
    private WebElement adultIncrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[1]//a[contains(@class, \"left\")]")
    private WebElement adultDecrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[1]//span[contains(@class, \"info\")]")
    private WebElement adultValueElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[2]//a[contains(@class, \"right\")]")
    private WebElement youthIncrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[2]//a[contains(@class, \"left\")]")
    private WebElement youthDecrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[2]//span[contains(@class, \"info\")]")
    private WebElement youthValueElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[3]//a[contains(@class, \"right\")]")
    private WebElement childIncrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[3]//a[contains(@class, \"left\")]")
    private WebElement childDecrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[3]//span[contains(@class, \"info\")]")
    private WebElement childValueElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[4]//a[contains(@class, \"right\")]")
    private WebElement toddlerIncrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[4]//a[contains(@class, \"left\")]")
    private WebElement toddlerDecrementElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//li[4]//span[contains(@class, \"info\")]")
    private WebElement toddlerValueElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"dropdown-tickets\")]//label[contains(@class, \"form-error\")]")
    private WebElement numberOfPersonsValidation;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item\")]/a[contains(@class, \"btn\") and contains(@class, \"continue\")]")
    private WebElement continueButton;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"item\")]/span[contains(@class, \"price\")]")
    private WebElement priceElement;
}




