# OrderMacbook
Tests to order Macbook and accessories from Apple website

## Prerequisites
1. Java 8
2. Apache Maven
3. Cucumber plugin
4. Lombok plugin
5. IDE (Intellij or Eclipse)

## Running the Tests Locally
### Running using IntelliJ IDE
You can trigger execution by going to Terminal and providing the below maven command.

### Running the tests using Maven
```
mvn clean test
```

## Reports

To check the execution report, navigate to `/taget/cucumber-html-report/index.html` and open the file in browser. For each pass test the report will appear in green mentioning the feature steps & data along with the screenshot for each of the step and for failed tests it will appear in red along with screenshot.

## About the code
The code runs on the Chrome browser and comprises of below files:

* Test Runner (TestRunner.java) - This is a JUnit test runner which triggers the cucumber execution.
* Feature File (OrderMacbook.feature) - This is a cucumber feature written in Gherkin Language.
* Step Definition (OrderMacbookStepDef.java) -  This is a glue code for the steps in feature file.
* Selenium Code (OrderMacbook.java) - This contains all the web elements and their implementation methods. The methods are callled from step definition file.
* Utility files:
    * (Generic.java) - This file contains the static driver and the common methods used to design the implementation.
    * (OperatingSystem.java) - This is an enum to identify the system of execution(Mac/ Windows) and depending on the same the code picks up the driver.
    * (Hooks.java) - This file comprises of the Hooks to be executed before and after the execution.
* The Chrome drivers for Mac and Windows machine are added in the Git Repo under `/drivers` folder.
 


Bugs:
1. The session is logged in even after closing the browser tab.
2. After reopening the link, if I click on login it stays on Home page.
3. No Validation on capacity factor
4. Length of asset name can be 33 chars with numbers or special chars
5. the capacity factor can have 0 and negative valur