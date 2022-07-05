package com.pexapark.pages;

import com.pexapark.util.Generic;
import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

public class Pexapark extends Generic {

    @FindBy(xpath = "//span[contains(text(), 'Mac')]/..")
    private WebElement selectMac;

    @FindBy(xpath = "//a[contains(text(), 'Buy')]")
    private WebElement buyButton;

    @FindBy(xpath = "//input[@id='colorOptionGridGroup_MVVM2B/A' and @datacolor='Silver']/..")
    private WebElement silverColor;

    @FindBy(xpath = "//div[@class='as-macbtr-options acc_MVVM2B/A rs-noAnimation as-bundleselection-modelshown']//button[@name='proceed']")
    private WebElement addToBag;

    @FindBy(xpath = "//span[@class='current_price']")
    private WebElement MacPrice;

    @FindBy(xpath = "//button[@name='add-to-cart']")
    private WebElement addToCart;

    @FindBy(name = "proceed")
    private WebElement checkOut;

    @FindBy(xpath = "//div[@data-autom='bagtotalvalue']")
    private WebElement bagTotal;

    @FindBy(xpath = "//div[@class='rs-tax-section']")
    private WebElement taxTotal;


    public Pexapark(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectMac(DataTable specifications) throws InterruptedException {
        try {
            //This is iterating on different data sets.
            for (Map<String, String> data : specifications.asMaps(String.class, String.class)) {
                selectMac.click(); //Selecting the Mac title link on Home page.\

                driver.findElement(By.xpath("//span[contains(text(), 'Pro " + data.get("Screen") + "')]")).click(); //Selecting the screensize as mentioned in data.

                buyButton.click(); //Click on Buy button.

                driver.findElement(By.xpath("//button[contains(text(), '" + data.get("Screen") + "')]")).click(); //Selecting the screen size from data.

                silverColor.click(); //Selecting the silver color.

                addToBag.click(); //Clicking on the Add To Bag button

                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains(text(),'" + data.get("MemoryRAM") + "')]"))); //Selecting the Memory size
                executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains(text(),'" + data.get("Storage") + "')]"))); //Selecting the Storage
                Thread.sleep(2000);
                executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains(text(),'" + data.get("Software") + "')]"))); //Selecting the Software
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void checkPrice(String expectedPrice) {
        try {
            String price = MacPrice.getText();
            Assert.assertEquals("The Price of Mac and Software is as expected.", price, expectedPrice); //Validating the Total Price of the Mac
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void addToCart() {
        addToCart.click(); //Click on Add to Cart
    }

    public void clickCheckout() {
        checkOut.click(); //Click on Checkout
    }

    public void selectAdapter(DataTable items) {
        try {
            List<Map<String, String>> data = items.asMaps(String.class, String.class);
            Assert.assertEquals("The Price of Mac and Software is as expected.", driver.findElement(By.xpath("//h3[contains(text(), '" + data.get(1).get("Option") + "')]/../../div[3]/span")).getText(), data.get(1).get("Price")); // Validating the price of Multiport adapter
            driver.findElement(By.xpath("//h3[contains(text(), '" + data.get(1).get("Option") + "')]/../../div[4]/async-add-bag/button")).click(); // Selecting the Multiport Adapter
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void validateCartTotal(String cartTotal) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bagTotal);
        Assert.assertEquals("Basket value is as expected.", bagTotal.getText(), cartTotal); // Validating the Basket Value Price
    }

    public void validateTotalTax(String totalTax) {
        Assert.assertEquals("Total Tax is as expected.", taxTotal.getText(), "Includes VAT of " + totalTax); // Validating the Total Tax.
    }
}
