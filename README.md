# Lambda Assignment

## Overview
This project automates web testing using Selenium with TestNG. It validates the page title and logo, interacts with checkboxes, and handles JavaScript alerts.

## Prerequisites
Ensure you have the following installed:
- Java (JDK 8 or later)
- Maven
- Chrome Browser
- ChromeDriver (Ensure compatibility with your Chrome version)

## Setup Instructions
1. Clone the repository or download the project.
2. Navigate to the project directory.
3. Install dependencies using Maven:
   ```sh
   mvn clean install
   ```

## Running the Tests
Execute the TestNG test suite using:
```sh
mvn test
```
Alternatively, you can run individual test classes from your IDE.

## Test Details
### 1. `validatePageTitleAndLogo()`
- Checks if the page title is correct.
- Verifies the LambdaTest logo is displayed.

### 2. `checkboxDemoTest()`
- Clicks on the "Checkbox Demo" link.
- Selects and unselects a checkbox while verifying state changes.

### 3. `testJavascriptAlert()`
- Navigates to the "JavaScript Alerts" section.
- Clicks the "Click Me" button.
- Validates and accepts the alert box.

## Notes
- Each test runs in the same browser instance.
- `@BeforeTest` initializes the WebDriver.
- `@BeforeMethod` ensures the base URL is opened before each test.
- `@AfterTest` closes the browser after all tests are executed.
- Timeouts are set to 20 seconds to handle slow responses.

## Troubleshooting
- If tests fail due to element visibility issues, increase wait times in `WebDriverWait`.
- Ensure `chromedriver.exe` is in `src/test/resources/` and matches your Chrome version.

## Contributors
- [Ranjitsinh]

