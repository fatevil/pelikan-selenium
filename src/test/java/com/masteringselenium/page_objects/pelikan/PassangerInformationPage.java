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

        // wait 20 seconds at most to display the header
        Wait<WebDriver> wait = new WebDriverWait(driver, 20);
        wait.until((ExpectedCondition<Boolean>) d -> {
            WebElement toDivElement = d.findElement(By.xpath("//div[@class = \"detail-header-wrapp-new-reservation\"]"));
            return toDivElement.isDisplayed();
        });
    }

    public WebElement getPassengerFirstnameTextArea() {
        return passengerFirstnameTextArea;
    }

    public WebElement getPassengerSurnameTextarea() {
        return passengerSurnameTextarea;
    }

    public WebElement getPassengerBirthDayInput() {
        return passengerBirthDayInput;
    }

    public WebElement getPassengerBirthMonthInput() {
        return passengerBirthMonthInput;
    }

    public WebElement getPassengerBirthYearInput() {
        return passengerBirthYearInput;
    }

    public WebElement getPassengerGenderInput() {
        return passengerGenderInput;
    }

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getPhoneInput() {
        return phoneInput;
    }

    public WebElement getCompanyStreetInput() {
        return companyStreetInput;
    }

    public WebElement getCompanyCityInput() {
        return companyCityInput;
    }

    public WebElement getCompanyZipInput() {
        return companyZipInput;
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

    @FindBy(how = How.XPATH, using = "//input[@id = \"radio-sex-1\"]")
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
}
