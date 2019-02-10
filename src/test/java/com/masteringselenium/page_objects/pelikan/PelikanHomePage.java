package com.masteringselenium.page_objects.pelikan;

import com.masteringselenium.page_objects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PelikanHomePage extends BasePage {

    public static final String PELIKAN_HOMEPAGE_URL = "https://www.pelikan.cz/cs";

    @FindBy(how = How.ID, using = "//*[@id=\"tab_tickets\" and contains(@class, \"active\")]")
    private WebElement ticketSearchElement;

    @FindBy(how = How.XPATH, using = "//ul[@class = \"list-tickets\"]/li[1]")
    private WebElement firstSalesTicketElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"lang-holder\")]//div[contains(@class, \"list-item-content\")]//span[contains(@class, \"current-lang\")]")
    private WebElement languageElement;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, \"lang-holder\")]//div[contains(@class, \"list-item-content\")]//span[contains(@class, \"currency\")]")
    private WebElement currencyElement;


    public static PelikanHomePage open(WebDriver driver) {
        driver.get(PELIKAN_HOMEPAGE_URL);

        PelikanHomePage homePage = new PelikanHomePage(driver);
        homePage.waitFor();
        homePage.initFields();
        return homePage;
    }

    private PelikanHomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitFor() {

        // wait 10 seconds at most for the homepage to load all the flight tickets
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) d -> {
            String saleTickets = d.findElement(By.xpath("//div[contains(@class, 'inner') and contains(@class ,'tickets')]/h3/span")).getText();
            return !saleTickets.isEmpty();
        });
    }

    /**
     * Gets the first sales ticket in the list. Focus on that div by @{@link Actions#moveToElement(WebElement)}.
     */
    public WebElement getFirstSalesTicketWebElement() {
        // move to element
        Actions actions = new Actions(driver);
        actions.moveToElement(firstSalesTicketElement);
        actions.perform();

        return firstSalesTicketElement;
    }

    /**
     * Gets the ticket search div element.
     */
    public WebElement getTicketSearchElement() {
        return ticketSearchElement;
    }

    /**
     * Get currently used language abbreviation.
     *
     * @return "CS" etc.
     */
    public String getCurrentLang() {
        return languageElement.getText();
    }

    /**
     * Get currently used currency abbreviation.
     *
     * @return "CZK" etc.
     */
    public String getCurrency() {
        return currencyElement.getText();
    }
}
