package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.pelikan.PelikanHomePage;
import com.masteringselenium.page_objects.pelikan.PelikanTicketDateSelectionPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

public class PelikanBuySalesTicketsTest extends DriverBase {

    private PelikanTicketDateSelectionPage salesTicketsPage;

    @BeforeMethod
    public void getToSalesTicketPage() {
        PelikanHomePage homePage = PelikanHomePage.open(driver);
        WebElement salesTicketElement = homePage.getFirstSalesTicketWebElement();
        salesTicketElement.click();

        this.salesTicketsPage = PelikanTicketDateSelectionPage.start(driver);
    }

    /**
     * Assert that after selecting correct start and return flight date, the price changes both times.
     */
    @Test
    public void choosingDateChangesPrice_successful() {
        WebElement originalPriceElement = salesTicketsPage.getPriceElement();
        String originalPriceText = originalPriceElement.getText();

        WebElement correctStartDateElement = salesTicketsPage.getCorrentStartDateFirstElement();
        correctStartDateElement.click();

        WebElement newPriceElement = salesTicketsPage.getPriceElement();
        String secondPriceText = newPriceElement.getText();

        assertThat(originalPriceText).isNotEqualTo(secondPriceText);

        WebElement correntToDateElement = salesTicketsPage.getCorrentToDateElement();
        correntToDateElement.click();

        WebElement finalPriceElement = salesTicketsPage.getPriceElement();
        String finalPriceText = finalPriceElement.getText();

        assertThat(secondPriceText).isNotEqualTo(finalPriceText);
    }


    /**
     * Assert that after selecting incorrect flight date, the continue button is still disabled.
     */
    @Test
    public void choosingIncorrectDateChangesPrice_unsuccessful() {
        WebElement correctDateElement = salesTicketsPage.getInactiveStartDate();
        correctDateElement.click();

        WebElement priceElement = salesTicketsPage.getPriceElement();
        assertThat(not(priceElement.isEnabled()));
    }

    /**
     * Assert that after selecting incorrect flight date, the continue button is still disabled.
     */
    @Test
    public void choosingInvalidDateChangesPrice_unsuccessful() {
        WebElement correctDateLastElement = salesTicketsPage.getCorrentStartDateLastElement();
        correctDateLastElement.click();

        WebElement disabledDate = salesTicketsPage.getDisabledToDate();
        disabledDate.click();

        WebElement priceElement = salesTicketsPage.getPriceElement();
        assertThat(not(priceElement.isEnabled()));
    }

    /**
     * Assure that first choosing the return date and then start date placed after the return date doesn't work.
     * <p>
     * Example: 5.1.2018 - 1.1.2018
     */
    @Test
    public void choosingReturnDateDeselectsStartDate_successful() {
        WebElement toPriceElement = salesTicketsPage.getCorrentToDateElement();
        toPriceElement.click();

        assertThat(toPriceElement.isSelected());

        WebElement startDateElement = salesTicketsPage.getCorrentStartDateFirstElement();
        startDateElement.click();

        assertThat(not(toPriceElement.isSelected()));
    }
}
