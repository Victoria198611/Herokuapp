HerokuApp UI Automation – Automated Testing Framework

This project represents a complete UI automation testing framework for the demo application The Internet – HerokuApp. The framework is built in a professional manner, following industry best practices: Java, Selenium WebDriver, TestNG, Maven, Page Object Model, and Allure Report.
Project Objective
The purpose of this project is to demonstrate:
•	strong QA Automation skills
•	a professional and scalable framework structure
•	CI/CD integration with GitHub Actions
•	automatic generation and publishing of Allure reports
•	automatic screenshots for failed tests
•	use of the Page Object Model for clean and maintainable code

Technologies Used
•	Java 17
•	Selenium WebDriver
•	TestNG
•	Maven
•	Page Object Model (POM)
•	Allure Report
•	GitHub Actions (CI/CD)
•	WebDriverManager

Project Structure
src
 └── test
     ├── java
     │    ├── pages
     │    │     ├── LoginPage.java
     │    │     └── SecurePage.java
     │    ├── tests
     │    │     └── LoginTests.java
     │    └── utils
     │          └── ScreenshotUtils.java
     └── resources
           └── environment.properties
           
Covered Functionalities
•	Login with valid credentials
•	Login with invalid credentials
•	Direct access to SecurePage without authentication
•	Logout and confirmation message validation
•	Automatic screenshots for failed tests
•	Allure Steps for clear and traceable actions in the report

Running Tests
1. Run locally
mvn clean test
2. Generate Allure report
mvn allure:report
3. View report locally
allure serve target/allure-results

Public Allure Report
The Allure report is automatically generated through GitHub Actions and published on GitHub Pages:
🔗 https://victoria198611.github.io/Herokuapp-Automation/
The report includes:
•	timeline
•	steps
•	environment
•	screenshots
•	suite overview

CI/CD – GitHub Actions
The pipeline runs automatically on every push to the main branch:
•	executes all tests
•	generates the Allure report
•	saves artifacts
•	publishes the report to the gh-pages branch

Automatic Screenshots
For every failed test, the framework automatically captures a screenshot and attaches it to the Allure report. Implementation can be found in:
utils/ScreenshotUtils.java

Page Object Model
The framework uses POM to separate UI logic from test logic.
Examples:
•	LoginPage — actions and elements for the login page (enter username, enter password, click Login, read messages, verify logout)
•	SecurePage — validations for the secure page (title verification, Logout button, navigation back to LoginPage)
•	BaseTest — WebDriver setup, application launch, window maximization, teardown, and automatic screenshot attachment for failed tests
•	LoginTests — test suites for valid login, invalid login, direct access to SecurePage, and logout
•	ScreenshotUtils — utility for automatic screenshots attached to Allure Report
•	environment.properties — Allure environment configuration (browser, OS, tester, versions)

Author
Victoria QA Automation Engineer Project created for professional portfolio and continuous development.
