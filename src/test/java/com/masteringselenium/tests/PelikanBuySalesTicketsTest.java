package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.pelikan.PelikanHomePage;
import com.masteringselenium.page_objects.pelikan.PelikanTicketDateSelectionPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
     * Assert that after selecting correct flight date, the price changes and the continue button is still disabled.
     */
    @Test
    public void choosingDateChangesPrice_successful() {
        WebElement originalPriceElement = salesTicketsPage.getPriceElement();
        String originalPriceText = originalPriceElement.getText();

        WebElement correctDateElement = salesTicketsPage.getCorrentStartDate();
        correctDateElement.click();

        WebElement newPriceElement = salesTicketsPage.getPriceElement();
        String newPriceText = newPriceElement.getText();

        assertThat(originalPriceText).isNotEqualTo(newPriceElement);
        assertThat(originalPriceText).isNotEqualTo(newPriceElement);
    }


    /**
     * Assert that after selecting incorrect flight date, the price does not change and continue button is still disabled.
     */
    @Test
    public void choosingDateChangesPrice_unsuccessful() {
        WebElement originalPriceElement = salesTicketsPage.getPriceElement();
        String originalPriceText = originalPriceElement.getText();

        WebElement correctDateElement = salesTicketsPage.getInactiveStartDate();
        correctDateElement.click();

        WebElement newPriceElement = salesTicketsPage.getPriceElement();
        String newPriceText = newPriceElement.getText();

        assertThat(originalPriceText).isEqualTo(newPriceText);
    }
}
