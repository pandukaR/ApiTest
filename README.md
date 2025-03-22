# Assurity Assignment API Testing

## Project Description

This project was created as part of a technical assignment given by Assurity Consulting. The task involves automating API response testing using REST-assured and generating Extent Reports for test execution results.

## Folder Structure

The project is organized as follows:

**src/main/java**: Contains all Java classes including:
  - **extentreports**: Manages the Extent Reports generation.
  - **listeners**: Contains listeners for the test execution.
  - **utils**: Contains helper classes such as `BaseApi`, `ConfigReader`, and `JsonUtils`.

**src/test/java**: Contains the test classes, including `ApiTest`, where the actual API test cases are implemented.

**src/main/resources**: Contains configuration files like `config.properties` and `expectedValues.json` expected JSON responses used for validation.

**report-output**: The output directory where Extent Reports are generated after the test execution.

## Dependencies

The project uses the following dependencies:

- **REST Assured**: For API testing.
- **TestNG**: For managing test execution.
- **Jackson**: For JSON handling.
- **Extent Reports**: For generating test execution reports.
- **JSONPath**: For extracting data from JSON responses.

## How to Run This Project Locally

Follow the steps below to set up and execute the tests on your local machine.

### 1. Clone the Repository

First, download the project by cloning the GitHub repository:

```bash
git clone https://github.com/pandukaR/ApiTest.git
```

Navigate into the project directory:

```bash
cd ApiTest
```

### 2. Verify Java Version

This project requires **Java 17** to compile and run correctly. However, the `pom.xml` file also includes configurations for Java 1.8. Ensure that you have **Java 17 installed and set as the active version**:

Check your Java version using:

```bash
java -version
```

If your Java version is different, update your system to use Java 17.

### 3. Install Maven

Maven is required to build and run the project. If you haven't installed it yet, download and install **Apache Maven** from [Maven's official website](https://maven.apache.org/download.cgi).

To verify Maven installation, run:

```bash
mvn -version
```

Ensure that Maven is correctly installed and linked to Java 17.

### 4. Run the Tests

Execute the tests using TestNG by running the following command:

```bash
mvn test
```

Alternatively, you can run the tests using the **testng.xml** file:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

### 5. View the Test Report

Once the test execution is complete, the report will be generated inside the `report-output` folder.

To view the test results, open the following file in a browser:

```
report-output/ExtentReport.html
```

This file provides a detailed summary of the test execution, including passed, failed, and skipped test cases.


