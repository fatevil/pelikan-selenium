package com.masteringselenium.page_objects.pelikan;

import com.masteringselenium.page_objects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassangerInformationPage extends BasePage {

    /**
     * Recognizes the driver and prepares new instance of this class for handling the page.
     *
     * @param driver
     * @return new instance of this class
     */
    public static PassangerInformationPage start(WebDriver driver) {
        PassangerInformationPage page = new PassangerInformationPage(driver);
        page.waitFor();
        page.initFields();
        return page;
    }

    private PassangerInformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitFor() {

        // wait 30 seconds at most to display the header
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until((ExpectedCondition<Boolean>) d -> {
            WebElement toDivElement = d.findElement(By.xpath("//div[@class = \"detail-header-wrapp-new-reservation\"]"));
            return toDivElement.isDisplayed();
        });
    }

    public WebElement getPassengerFirstnameTextArea() {
        PelikanWebDriverUtils.moveToElement(driver, passengerFirstnameTextArea);
        return passengerFirstnameTextArea;
    }

    public WebElement getPassengerSurnameTextarea() {
        PelikanWebDriverUtils.moveToElement(driver, passengerSurnameTextarea);
        return passengerSurnameTextarea;
    }

    public WebElement getPassengerBirthDayInput() {
        PelikanWebDriverUtils.moveToElement(driver, passengerBirthDayInput);
        return passengerBirthDayInput;
    }

    public WebElement getPassengerBirthMonthInput() {
        PelikanWebDriverUtils.moveToElement(driver, passengerBirthMonthInput);
        return passengerBirthMonthInput;
    }

    public WebElement getPassengerBirthYearInput() {
        PelikanWebDriverUtils.moveToElement(driver, passengerBirthYearInput);
        return passengerBirthYearInput;
    }

    public WebElement getPassengerGenderInput() {
        PelikanWebDriverUtils.moveToElement(driver, passengerGenderInput);
        return passengerGenderInput;
    }

    public WebElement getEmailInput() {
        PelikanWebDriverUtils.moveToElement(driver, emailInput);
        return emailInput;
    }

    public WebElement getPhoneInput() {
        PelikanWebDriverUtils.moveToElement(driver, phoneInput);
        return phoneInput;
    }

    public WebElement getCompanyStreetInput() {
        PelikanWebDriverUtils.moveToElement(driver, companyStreetInput);
        return companyStreetInput;
    }

    public WebElement getCompanyCityInput() {
        PelikanWebDriverUtils.moveToElement(driver, companyCityInput);
        return companyCityInput;
    }

    public WebElement getCompanyZipInput() {
        PelikanWebDriverUtils.moveToElement(driver, companyZipInput);
        return companyZipInput;
    }

    public WebElement getContinueButton() {
        PelikanWebDriverUtils.moveToElement(driver, continueButton);
        return continueButton;
    }

    @FindBy(how = How.XPATH, using = "//input[@name = \"formDetailPassengersName\"]")
    private WebElement passengerFirstnameTextArea;

    @FindBy(how = How.XPATH, using = "//input[@name = \"formDetailPassengersSurname\"]")
    private WebElement passengerSurnameTextarea;

    @FindBy(how = How.XPATH, using = "//input[@id = \"daybirthDate0\"]")
    private WebElement passengerBirthDayInput;

    @FindBy(how = How.XPATH, using = "//input[@id = \"monthbirthDate0\"]")
    private WebElement passengerBirthMonthInput;

    @FindBy(how = How.XPATH, using = "//input[@id = \"yearbirthDate0\"]")
    private WebElement passengerBirthYearInput;

    @FindBy(how = How.XPATH, using = "//input[@id = \"radio-sex-1\"]/parent::div")
    private WebElement passengerGenderInput;

    @FindBy(how = How.XPATH, using = "//input[@name = \"formDetailContactEmail\"]")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@name = \"formDetailContactPhone\"]")
    private WebElement phoneInput;

    @FindBy(how = How.XPATH, using = "//input[@name = \"formDetailContactCompanyStreet\"]")
    private WebElement companyStreetInput;

    @FindBy(how = How.XPATH, using = "//input[@name = \"formDetailContactCompanyCity\"]")
    private WebElement companyCityInput;

    @FindBy(how = How.XPATH, using = "//input[@name = \"formDetailContactZip\"]")
    private WebElement companyZipInput;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"summary-button-new-reservation\")]/a[contains(@class, \"btn-green\")]")
    private WebElement continueButton;

}
