package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;

// assertJ http://joel-costigliola.github.io/assertj/
// Select https://www.guru99.com/select-option-dropdown-selenium-webdriver.html

public class WebElementsTests extends DriverBase {

    @Test
    public void assertText_findById() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement element = driver.findElement(By.id("divontheleft"));
        Assert.assertEquals("Assert that this text is on the page", element.getText());
    }

    @Test
    public void assertText_findByClass() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement element = driver.findElement(By.className("leftdiv"));
        Assert.assertEquals("Assert that this text is on the page", element.getText());
    }

    @Test
    public void checkRadioButton() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement element = driver.findElement(By.id("radiobutton"));
        Assert.assertFalse(element.isSelected());
    }

    @Test
    public void selectOption_basic() throws Exception {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement select = driver.findElement(By.tagName("select"));
        List<WebElement> options = select.findElements(By.tagName("option"));
        assertThat(options).isNotEmpty();
        System.out.println(options.stream().map(e -> e.getText()).collect(Collectors.joining(",")));

        options.get(2).click();

        WebElement selectedOption = options.stream().filter(o -> o.isSelected()).findFirst().orElseThrow(() -> new Exception("Selected OPTION not found"));

        Optional<WebElement> alternative = options.stream().filter(o -> o.isSelected()).findFirst();
        assertThat(alternative.isPresent()).isTrue();

        assertThat(selectedOption.getText()).isEqualToIgnoringCase("Selenium RC");
    }

    @Test
    public void selectOption_withClass() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");

        WebElement selectElement = driver.findElement(By.id("selecttype"));
        Select select = new Select(selectElement);
        List<WebElement> options = select.getOptions();
        assertThat(options).isNotEmpty();
        System.out.println(options.stream().map(e -> e.getText()).collect(Collectors.joining(",")));

        select.selectByIndex(0);
        assertThat(select.getFirstSelectedOption()).isNotNull();
        assertThat(select.getFirstSelectedOption().getText()).isEqualTo("Selenium IDE");
    }

    @Test
    public void clickAndWaitForAjax() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");

        WebElement link = driver.findElement(By.id("loadajax"));
        WebElement div = driver.findElement(By.id("ajaxdiv"));

        Assert.assertEquals("", div.getText().trim());
        assertThat(div.getText().trim()).isEmpty();

        link.click();
        waitForJSandJQueryToLoad(driver);

        assertThat(div.getText().trim()).isNotEmpty();
    }
}