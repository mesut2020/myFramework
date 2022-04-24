Feature: Login Page Functionality with invalid credentials

  Scenario: User cannot login with invalid credentials

    Given user on home page "https://opensource-demo.orangehrmlive.com/"
    When user login with invalid credentials from Excel File
    Then User see the error message "Invalid Credentials"