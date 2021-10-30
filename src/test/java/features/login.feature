Feature: Login Page Functionality with valid credentials
@Smoke
  Scenario: User can login with valid credentials

    Given user on home page "https://opensource-demo.orangehrmlive.com/"
    When user login as "Admin" and password as "admin123"
    Then login should be successful