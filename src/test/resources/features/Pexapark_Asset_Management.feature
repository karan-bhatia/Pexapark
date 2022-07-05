Feature: Test Pexapark web application
  As a customer representative
  I want to add/edit/delete assets in the pexapark application
  So that I can manage my assets

  Background:
    Given the url "https://test-monitor-qa.pexapark.com"
    Then Login to the application with user name "test-user-1" and password "ae8743rzq36cq2x"

  @Add_And_Delete_Asset
  Scenario Outline: Add and delete a new asset to the application
    Given Validate you are on Add Asset page
    When Enter the name as "<Asset_Name>" and capacity factor as "<Capacity_Factor>"
    Then Click on Submit
    And Validate the asset is added in the table for "<Validation_Message>" and asset name "<Asset_Name>"
    And If "<Validation_Message>" is success then delete the asset with name as "<Asset_Name>"

    Examples:
      | Asset_Name | Capacity_Factor | Validation_Message |
      | Test123    | 1212            | Success            |

  @Add_Edit_Delete_Asset
  Scenario Outline: Add, edit and delete a new asset to the application
    Given Validate you are on Add Asset page
    When Enter the name as "<Asset_Name>" and capacity factor as "<Capacity_Factor>"
    Then Click on Submit
    And Validate the asset is added in the table for "<Validation_Message>" and asset name "<Asset_Name>"
    And If "<Validation_Message>" is success with asset name as "<Asset_Name>" then edit the the asset with name as "<Asset_Name_New>" and capacity factor as "<Capacity_Factor_New>"
    And If "<Validation_Message>" is success then delete the asset with name as "<Asset_Name_New>"

    Examples:
      | Asset_Name | Capacity_Factor | Validation_Message |Asset_Name_New|Capacity_Factor_New|
      | Test123    | 1212            | Success            |Test1234      |787898             |
