package com.pexapark.util;

import cucumber.api.Scenario;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Generic {

    public static WebDriver driver;

    public static Scenario scenario;

    @Setter
    @Getter
    private String url;

    public void initDriver(String browserType) {
        switch (browserType) {
            case "Chrome":
                driver = initChromeDriver();
                break;
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
                driver = initChromeDriver();
        }
    }

    private WebDriver initChromeDriver() {
        System.out.println("\n\nLaunching google chrome");
        if (OperatingSystem.isMac()) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "/drivers/mac/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\drivers\\windows\\chromedriver.exe");
        }
        ChromeOptions options = new ChromeOptions();
        options.setCapability("capability_name", "capability_value");
        options.setCapability("type", "test-type");
        options.setCapability("windowSize", "start-maximized");
        options.setCapability("automation", "--enable-automation");
        options.setCapability("browserType", "test-type=browser");
        options.setCapability("infobars", "disable-infobars");
        options.setExperimentalOption("useAutomationExtension", false);
        return new ChromeDriver(options);
    }

    public void launchURL(String url) {
        driver.get(url);
    }

    public static void takeScreenShot() {
        // fileName of the screenshot
        Date d = new Date();
        String screenshotFileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
        // store screenshot in that file

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile,
                    new File(System.getProperty("user.dir") + "/target/Screenshots/" + screenshotFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] scrBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(scrBytes, "image/png");
    }

    public void initializeTestBaseSetup(String browser) {
        try {
            initDriver(browser);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
