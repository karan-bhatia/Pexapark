# Pexapark
Tests to add, edit and delete asset in asset management application

##Approach to Testing
 ###Exploratory Testing
* Check all the tabs. Home page is displayed on clicking on Home, Asset and Logout.
* On click on Login, login page is displayed.
* Login with valid and invalid credentials. For invalid credentials, correct message is displayed.
* After logged in, click on logout. The user is logged out and Home page is displayed.
* Login again and add an asset. An asset is added successfully.
* Edit an already existing asset and provide new name and capacity factor. The details are updated on the click of submit button.
* Delete an already existing asset. The asset is deleted successfully with no confirmation message.
* Close the browser while user is logged in. Now reopen the application and user will land on Home page.
* Click on Asset tab, the user is already logged in and user lands on Add asset page.
* Add duplicate asset name and the application throws an error.


##Test Automation

Automation is written using selenium, java, cucumber and feature file (BDD). 
A report is created after all the test cases are executed

###Scenario:
* Background:
  * The script loads the Chrome browser.
  * The asset management web link is loaded
  * The user logs in to the application.
  

* **Test Scenario - 1 : Add and delete a asset**
  * User adds a new asset with the name provided in the examples.
  * User clicks on Submit button
  * The code validates the asset is added in the table shown on the page.
  * If the asset is added successfully, then the user deletes the asset. This will help us in testing the delete functionality and also to execute the test case multiple times without changing the asset name (As duplicate asset name are not accepted).
  * The entire scenario is tested with multiple data sets.
    * Valid asset name and valid capacity factor
    * Asset name as 33 characters with special characters and numbers and capacity factor as negative
    * Asset name as 33 characters with special characters and numbers and capacity factor as zero
    * Asset name as 33 characters with special characters and numbers and capacity factor as decimals
    * Asset name more than 33 characters. (This is a negative scenario). Here the error message displayed is validated by the code.
    * **Negative scenario** - Duplicate asset name. Here the error message displayed is validated by the code.(Assuming there is already a asset with same name exists.)

* **Test Scenario - 2 : Add, edit and delete a asset**
  * User adds a new asset with the name provided in the examples.
  * User clicks on Submit button
  * The code validates the asset is added in the table shown on the page.
  * If the asset is added successfully, then the user edits the asset. This will help us in testing the edit functionality.
  * If the asset is added successfully, then the user deletes the asset. The asset added and edited is now deleted.
  * The entire scenario is tested with multiple data sets.
    * Valid asset name and valid capacity factor
    * Asset name as 33 characters with special characters is added. The added asset is now edited with 34 characters asset name. This should throw and error as 34 characters is not accepted.


### Prerequisites
1. Java 8
2. Apache Maven
3. Cucumber plugin
4. Lombok plugin
5. IDE (Intellij or Eclipse)

### Running the Tests Locally
Clone the repo in your local machine and execute below.

#### Running using IntelliJ IDE
* Open the project in Intellij. 
* You can trigger execution by going to Terminal and providing the maven command **mvn clean test**.
* If the above doesnt work then you can trigger the executin by right click on feature file and Run feature.

#### Running the tests using bash
* After clonning the project in local machine, go to project repo in bash and execute below command. 
```
mvn clean test
```

### Reports

To check the execution report, navigate to `/taget/cucumber-html-report/index.html` and open the file in browser. For each pass test the report will appear in green mentioning the feature steps & data along with the screenshot for each of the step and for failed tests it will appear in red along with screenshot.

### About the test automation code
The code runs on the Chrome browser and comprises of below files:

* Test Runner (RunTest.java) - This is a JUnit test runner which triggers the cucumber execution.
* Feature File (Pexapark_Asset_Mgmt.feature) - This is a cucumber feature written in Gherkin Language.
* Step Definition (Pexapark_StepDef.java) -  This is a glue code for the steps in feature file. This contains all the web elements and their implementation methods.
* Utility files:
    * (Generic.java) - This file contains the static driver and the common methods used to design the implementation.
    * (OperatingSystem.java) - This is an enum to identify the system of execution(Mac/ Windows) and depending on the same the code picks up the driver.
    * (Hooks.java) - This file comprises of the Hooks to be executed before and after the execution.
* The Chrome drivers for Mac and Windows machine are added in the Git Repo under `/drivers` folder.

## Bugs
* The session is logged in even after closing the browser tab. After reopening the link, if I click on login it stays on Home page.
* No Validation on capacity factor. It can be 0 or negative value
* Length of asset name can be 33 chars with numbers or special chars, instead of 30 chars
* When there is data missing on the edit page it is showing “Server Error” which is not correct error message.

