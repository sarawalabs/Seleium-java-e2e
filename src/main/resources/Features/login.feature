#Author: sarawa@microsoft.com
#Keywords Summary : This is a BDD framework desription
@DFMLogin
Feature: DFM Login
  Verify that user should be able to access the TFS Homepage

  Scenario Outline: Verify user should be able to access the TFS Homepage
    Given The user Launches the Browser
    When User enters the url in the browser
    And A homepage must be displayed with the name <HomePage>
    And The collection name <CollectionName> is also visible
    Then Close the browser

    Examples: 
      | HomePage     | CollectionName |
      | Azure DevOps | samrawlab      |
