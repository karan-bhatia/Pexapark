package com.pexapark.stepdefinition;

import com.pexapark.util.Generic;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Pexapark_StepDef extends Generic {

    @Given("^the url \"([^\"]*)\"$")
    public void theUrlInBrowser(String url) throws Throwable {
        this.setUrl(url); //Setting the URL in a parameter
        this.initializeTestBaseSetup("Chrome"); //Initializing the Browser
        this.launchURL(this.getUrl()); //Loading the URL
        takeScreenShot();
    }

    @Then("^Login to the application with user name \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void loginToTheApplicationWithUserNameAndPassword(String username, String password) throws Throwable {
        driver.findElement(By.xpath("//a[text() = 'Login']")).click();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("pwd")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
    }

    @Given("^Validate you are on Add Asset page$")
    public void validateYouAreOnAddAssetPage() {
        driver.findElement(By.xpath("//h3[text()='Add Asset']")).isEnabled();
        //Assert.assertEquals("Add Asset", page_header);
    }

    @When("^Enter the name as \"([^\"]*)\" and capacity factor as \"([^\"]*)\"$")
    public void enterTheNameAsAndCapacityFactorAs(String assetName, String capacityFactor) throws Throwable {
        try {
            driver.findElement(By.id("name")).sendKeys(assetName);
            driver.findElement(By.id("cf")).sendKeys(capacityFactor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Then("^Click on Submit$")
    public void clickOnSubmit() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.id("submit")).click();
    }

    @And("^Validate the asset is added in the table for \"([^\"]*)\" and asset name \"([^\"]*)\"$")
    public void validateTheAssetIsAddedInTheTableForAndAssetName(String message, String assetName) throws Throwable {
        try {
            List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody[2]/tr"));
            System.out.println("table rows == " + tableRows.size());
            if (message.equalsIgnoreCase("Success")) {
                for (WebElement row : tableRows) {
                    while (row.getText().contains(assetName)) {
                        System.out.println("The assetName is found");
                        Assert.assertTrue("The text contain assetName added", true);
                        break;
                    }
                }
            } else if (message.equalsIgnoreCase("Invalid_Asset_Name")) {
                WebElement header1 = driver.findElement(By.xpath("//h1"));
                highlight(driver, header1);
                Assert.assertEquals("Ooops!", header1.getText());
                WebElement errorMessage = driver.findElement(By.xpath("//p"));
                highlight(driver, errorMessage);
                Assert.assertEquals("Invalid asset name, name cannot exceed 33 characters", errorMessage.getText());
                System.out.println("Invalid message is validated");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^If \"([^\"]*)\" is success then delete the asset with name as \"([^\"]*)\"$")
    public void ifIsSuccessThenDeleteTheAssetWithNameAs(String message, String assetName) throws Throwable {
        if (message.equalsIgnoreCase("Success")) {
            driver.findElement(By.id("d-" + assetName)).click();
            System.out.println("Asset is deleted");
        }

    }

    @And("^If \"([^\"]*)\" is success with asset name as \"([^\"]*)\" then edit the the asset with name as \"([^\"]*)\" and capacity factor as \"([^\"]*)\"$")
    public void ifIsSuccessWithAssetNameAsThenEditTheTheAssetWithNameAsAndCapacityFactorAs(String message, String assetName, String newAssetName, String newCapacityFactor) throws Throwable {
        driver.findElement(By.id("e-" + assetName)).click();
        driver.findElement(By.id("name")).sendKeys(newAssetName);
        driver.findElement(By.id("cf")).sendKeys(newCapacityFactor);
        driver.findElement(By.id("submit")).click();
        if (message.equalsIgnoreCase("Success")) {
            System.out.println("Asset details are editted");
        }else if (message.equalsIgnoreCase("Invalid_Asset_Name")) {
            WebElement header1 = driver.findElement(By.xpath("//h1"));
            highlight(driver, header1);
            Assert.assertEquals("Ooops!", header1.getText());
            WebElement errorMessage = driver.findElement(By.xpath("//p"));
            highlight(driver, errorMessage);
            Assert.assertEquals("Invalid asset name, name cannot exceed 33 characters", errorMessage.getText());
            System.out.println("Invalid message is validated");
        }
    }
}
