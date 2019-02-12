package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.pelikan.PassangerInformationPage;
import com.masteringselenium.page_objects.pelikan.PelikanHomePageObject;
import com.masteringselenium.page_objects.pelikan.SalesTicketDatePickPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PassengerInformationPageTest extends DriverBase {

    private PassangerInformationPage passengerInformationPage;

    @BeforeMethod
    public void goToPassengerInfoPage() {
        PelikanHomePageObject homePage = PelikanHomePageObject.open(driver);
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
    public void addingAdultDoublesThePrice() {

        // :)

    }


}
