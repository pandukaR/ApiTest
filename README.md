# Assurity Assignment API Testing

## Project Description

This project was created as part of an assignment given to me during my engagement with Assurity Consulting. The task involves automating the testing of API responses using REST-assured and generating Extent Reports for test execution results.

## Folder Structure

The project is organized as follows:

- **src/main/java**: Contains all Java classes including:
  - **extentreports**: Manages the Extent Reports generation.
  - **listeners**: Contains listeners for the test execution.
  - **utils**: Contains helper classes such as `BaseApi`, `ConfigReader`, and `JsonUtils`.
- **src/test/java**: Contains the test classes, including `ApiTest`, where the actual API test cases are implemented.
- **src/main/resources**: Contains configuration files like `config.properties` and expected JSON responses used for validation.
- **report-output**: The output directory where Extent Reports are generated after the test execution (not tracked in Git).

## Dependencies

The project uses the following dependencies:

- **REST Assured**: For API testing.
- **TestNG**: For managing test execution.
- **Jackson**: For JSON handling.
- **Extent Reports**: For generating test execution reports.
- **JSONPath**: For extracting data from JSON responses.

## Running the Tests

To run the tests, follow the steps below:

1. Clone the repository:
   ```bash
   git clone https://github.com/pandukaR/ApiTest.git
   ```
