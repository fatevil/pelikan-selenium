package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.pelikan.PelikanHomePage;
import com.masteringselenium.page_objects.pelikan.SalesTicketDatePickFactory;
import com.masteringselenium.page_objects.pelikan.SalesTicketPersonPickFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SalesTicketPersonPickTest extends DriverBase {

    private SalesTicketPersonPickFactory personPickPage;

    @BeforeMethod
    public void getToSalesTicketPage() {
        PelikanHomePage homePage = PelikanHomePage.open(driver);
        WebElement salesTicketElement = homePage.getFirstSalesTicketWebElement();
        salesTicketElement.click();

        SalesTicketDatePickFactory salesTicketsPage = SalesTicketDatePickFactory.start(driver);
        WebElement correctStartDateElement = salesTicketsPage.getCorrentStartDateFirstElement();
        correctStartDateElement.click();
        WebElement correntToDateElement = salesTicketsPage.getCorrectToDateElement();
        correntToDateElement.click();

        this.personPickPage = SalesTicketPersonPickFactory.start(driver);
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
     * Assert that if no one is selected, the price is 0.
     */
    @Test
    public void zeroPeopleZeroPrice() {
        WebElement adultIncrementElement = personPickPage.getAdultDecrementElement();
        adultIncrementElement.click();

        int finalPrice = personPickPage.getPrice();

        assertThat(finalPrice).isEqualTo(0);
    }

    /**
     * Assert that none of the selectors can be less than zero.
     */
    @Test
    public void cantGoToNegativeNumberOfPersons() {
        WebElement adultIncrementElement = personPickPage.getAdultDecrementElement();
        adultIncrementElement.click();
        adultIncrementElement.click();
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

}
