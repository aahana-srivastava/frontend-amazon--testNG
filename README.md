# ecomtest Selenium Project

## Overview

This project contains an automated test suite for an e-commerce website (Amazon.in) using Selenium WebDriver with Java. The test demonstrates a basic product search, selection, and checkout flow.

**Note:** This test script is currently in a fragile state due to potential DOM changes on the target website. It may require frequent updates to maintain functionality.

## Project Setup

### Prerequisites

- Java JDK 8 or higher
- Maven
- Chrome browser
- ChromeDriver (compatible with your Chrome version)

### Dependencies

- Selenium WebDriver
- TestNG
- WebDriverManager (for managing driver executables)

## How to Run the Tests

1. Clone this repository to your local machine.
2. Open a terminal and navigate to the project root directory.
3. Update the ChromeDriver path in the `setUp()` method of `ecomtest.java`:
   ```java
   System.setProperty("webdriver.chrome.driver", "path/to/your/chromedriver");
   ```
4. Run the following command to execute the tests:
   ```
   mvn test
   ```

## Framework Design and Structure

The test automation framework is built using the following components:

1. **WebDriver**: Selenium WebDriver is used to interact with the web browser.
2. **TestNG**: Used as the test runner and for test annotations.
3. **Page Object Model**: Although not fully implemented in the current version, the structure is set up to support POM for better maintainability.
4. **WebDriverWait**: Utilized for handling dynamic elements and improving test stability.

### Key Components:

- `ecomtest.java`: Contains the main test class with setup, teardown, and test methods.
- `searchAndNavigateProduct()`: The core method that performs the e-commerce flow.

## Known Issues and Limitations

1. The script is highly dependent on the current DOM structure of Amazon.in. Any changes to the website's layout or element identifiers may break the test.
2. Error handling is minimal, which may lead to test failures without clear reasons.
3. The script uses hard-coded selectors, making it fragile to website updates.

## Future Improvements

1. Implement a robust Page Object Model to improve maintainability.
2. Add more comprehensive error handling and logging.
3. Implement data-driven testing to cover more scenarios.
4. Add support for cross-browser testing.
5. Implement a more flexible element location strategy to handle DOM changes.

## Test Report

Currently, the test report is limited to console output. To generate a comprehensive test report:

1. Integrate a reporting tool like Extent Reports or Allure.
2. Add screenshot capture functionality for key steps.
3. Implement detailed logging throughout the test execution.

## Maintenance

Due to the fragile nature of the current implementation, regular maintenance is required:

1. Periodically review and update element selectors.
2. Monitor for changes in the Amazon.in website structure and flow.
3. Consider implementing more robust element location strategies (e.g., custom attributes) in collaboration with the development team.

## Contributing

Contributions to improve the stability and functionality of this test suite are welcome. Please submit pull requests with proposed changes.
