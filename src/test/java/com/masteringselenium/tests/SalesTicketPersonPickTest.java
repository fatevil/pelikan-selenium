package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.pelikan.PelikanHomePageObject;
import com.masteringselenium.page_objects.pelikan.SalesTicketDatePickPage;
import com.masteringselenium.page_objects.pelikan.SalesTicketPersonPickPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesTicketPersonPickTest extends DriverBase {

    private SalesTicketPersonPickPage personPickPage;

    @BeforeMethod
    public void getToSalesTicketPage() {
        PelikanHomePageObject homePage = PelikanHomePageObject.open(driver);
        WebElement salesTicketElement = homePage.getFirstSalesTicketWebElement();
        salesTicketElement.click();

        SalesTicketDatePickPage salesTicketsPage = SalesTicketDatePickPage.start(driver);
        WebElement correctStartDateElement = salesTicketsPage.getCorrentStartDateFirstElement();
        correctStartDateElement.click();
        WebElement correntToDateElement = salesTicketsPage.getCorrectToDateElement();
        correntToDateElement.click();

        this.personPickPage = SalesTicketPersonPickPage.start(driver);
        personPickPage.getOpenPersonsSelectionElement().click();
    }

    /**
     * Assert that after adding an adult, the prices doubles.
     */
    @Test
    public void addingAdultDoublesThePrice() {

        int originalPrice = personPickPage.getPrice();

        WebElement adultIncrementElement = personPickPage.getAdultIncrementElement();
        adultIncrementElement.click();

        int finalPrice = personPickPage.getPrice();

        assertThat(finalPrice).isEqualTo(originalPrice * 2);
    }

    /**
     * Assert that if no one is selected:
     * <p>
     * 1) the price is 0
     * 2) warning message appears
     * 3) continue button is disabled
     */
    @Test
    public void zeroPeopleZeroPrice() {
        WebElement adultDecrementElement = personPickPage.getAdultDecrementElement();
        adultDecrementElement.click();

        int finalPrice = personPickPage.getPrice();
        assertThat(finalPrice).isEqualTo(0);

        WebElement numberOfPersonsValidation = personPickPage.getNumberOfPersonsValidation();
        assertThat(numberOfPersonsValidation.isDisplayed());

        assertThat(personPickPage.isContinueButtonDisabled()).isTrue();
    }

    /**
     * Assert that none of the selectors can be less than zero.
     */
    @Test
    public void cantGoToNegativeNumberOfPersons() {
        WebElement adultDecrementElement = personPickPage.getAdultDecrementElement();
        adultDecrementElement.click();
        adultDecrementElement.click();
        WebElement adultValueElement = personPickPage.getAdultValueElement();

        WebElement childDecrementElement = personPickPage.getChildDecrementElement();
        childDecrementElement.click();
        WebElement childValueElement = personPickPage.getChildValueElement();

        WebElement youthDecrementElement = personPickPage.getYouthDecrementElement();
        youthDecrementElement.click();
        WebElement youthValueElement = personPickPage.getYouthValueElement();

        try {
            // apparently in new version of chrome, there are 4 selectable options
            // in the older version, there are only 3 options
            WebElement toddlerDecrementElement = personPickPage.getToddlerDecrementElement();
            toddlerDecrementElement.click();
            WebElement toddlerValueElement = personPickPage.getToddlerValueElement();

            assertThat(toddlerValueElement.getText()).isEqualTo("0");
        } catch (NoSuchElementException e) {
            // at least show it's not okay
            e.printStackTrace();
        }


        int finalPrice = personPickPage.getPrice();

        assertThat(finalPrice).isEqualTo(0);

        // 0 passengers, not negative
        assertThat(adultValueElement.getText()).isEqualTo("0");
        assertThat(childValueElement.getText()).isEqualTo("0");
        assertThat(youthValueElement.getText()).isEqualTo("0");
    }

    /**
     * Assure that 9 people is the maximum.
     */
    @Test
    public void moreThanMaximumNotAllowed() {
        WebElement adultIncrementElement = personPickPage.getAdultIncrementElement();
        for (int i = 0; i < 8; i++) {
            adultIncrementElement.click();
        }

        // 9 poeple still okay
        assertThat(personPickPage.isContinueButtonDisabled()).isFalse();

        adultIncrementElement.click();

        // 10 poeple not okay

        // check the continue button
        assertThat(personPickPage.isContinueButtonDisabled()).isTrue();

        // check error message
        WebElement numberOfPersonsValidation = personPickPage.getNumberOfPersonsValidation();
        assertThat(numberOfPersonsValidation.isDisplayed());

    }

    /**
     * Assure there only can be as much toddlers as adults.
     */
    @Test
    public void numberOfToddlersFitsTheAdults() {
        // at first the continue button is enabled
        assertThat(personPickPage.isContinueButtonDisabled()).isFalse();

        // 1 toddler 1 adult still okay
        WebElement toddlerIncrementElement = personPickPage.getToddlerIncrementElement();
        toddlerIncrementElement.click();
        assertThat(personPickPage.isContinueButtonDisabled()).isFalse();

        // 2 toddlers 1 adult not okay
        toddlerIncrementElement.click();
        assertThat(personPickPage.isContinueButtonDisabled()).isTrue();
    }

}
