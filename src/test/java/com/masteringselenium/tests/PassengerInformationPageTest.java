package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.pelikan.PassangerInformationPage;
import com.masteringselenium.page_objects.pelikan.PelikanHomePage;
import com.masteringselenium.page_objects.pelikan.SalesTicketDatePickPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PassengerInformationPageTest extends DriverBase {

    private PassangerInformationPage passengerInformationPage;

    @BeforeMethod
    public void goToPassengerInfoPage() {
        PelikanHomePage homePage = PelikanHomePage.open(driver);
        WebElement salesTicketElement = homePage.getFirstSalesTicketWebElement();
        salesTicketElement.click();

        SalesTicketDatePickPage salesTicketsPage = SalesTicketDatePickPage.start(driver);
        WebElement correctStartDateElement = salesTicketsPage.getCorrentStartDateFirstElement();
        correctStartDateElement.click();
        WebElement correntToDateElement = salesTicketsPage.getCorrectToDateElement();
        correntToDateElement.click();

        WebElement continueButton = salesTicketsPage.getContinueButton();
        continueButton.click();

        this.passengerInformationPage = PassangerInformationPage.start(driver);
    }

    /**
     *
     */
    @Test
    public void successfulWalkThrough() {
        // first part of the form
        passengerInformationPage.getPassengerGenderInput().click();
        passengerInformationPage.getPassengerFirstnameTextArea().sendKeys("FirstName");
        passengerInformationPage.getPassengerSurnameTextarea().sendKeys("Surname");
        passengerInformationPage.getPassengerBirthDayInput().sendKeys("1");
        passengerInformationPage.getPassengerBirthMonthInput().sendKeys("1");
        passengerInformationPage.getPassengerBirthYearInput().sendKeys("1985");

        // second part of the form
        passengerInformationPage.getEmailInput().sendKeys("test@test.cz");
        passengerInformationPage.getPhoneInput().sendKeys("789789789");
        passengerInformationPage.getCompanyCityInput().sendKeys("Praha");
        passengerInformationPage.getCompanyStreetInput().sendKeys("Random Street 12");
        passengerInformationPage.getCompanyZipInput().sendKeys("11111");

        // continue
        passengerInformationPage.getContinueButton().click();

        // skip the insurance page
        passengerInformationPage.getContinueButton().click();

    }


}
