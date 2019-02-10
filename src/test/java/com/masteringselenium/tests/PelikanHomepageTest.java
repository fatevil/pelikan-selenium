package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.page_objects.pelikan.PelikanHomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PelikanHomepageTest extends DriverBase {

    private PelikanHomePage homePage;

    /**
     * Open homepage and assure that the selected language and currency is czech.
     */
    @Test
    public void czechHomepageLanded_successful() {
        this.homePage = PelikanHomePage.open(driver);

        assertThat(homePage.getCurrency()).isEqualTo("(CZK)");
        assertThat(homePage.getCurrentLang()).isEqualTo("CS");
    }

    /**
     * Assure that there is ticket search div and it's active and visible.
     */
    @Test(dependsOnMethods = "czechHomepageLanded_successful")
    public void ticketSearchIsPresent() {
        WebElement ticketSearch = homePage.getFirstSalesTicketWebElement();
        assertThat(ticketSearch.isDisplayed());
    }


    /**
     * Assure that there is a sales tickets div. Focus on that div by @{@link Actions#moveToElement(WebElement)}.
     */
    @Test(dependsOnMethods = "czechHomepageLanded_successful", groups = "homepage.salesTicket")
    public void salesTicketsListIsPresent() {
        WebElement salesTicketElement = homePage.getFirstSalesTicketWebElement();
        assertThat(salesTicketElement.isDisplayed());
    }

}

