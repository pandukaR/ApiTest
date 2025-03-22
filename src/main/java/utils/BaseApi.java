package utils;


import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.jayway.jsonpath.JsonPath;
import com.relevantcodes.extentreports.LogStatus;

import extentreports.ExtentTestManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Base class for API testing that includes common methods for making API requests and validating responses.
 */
public class BaseApi {
	
	public static RequestSpecification request;
	private SoftAssert softAssert;
	
	/**
	 * Adds headers to the RestAssured request.
	 *
	 * @param headers A string containing headers and their values, separated by "&".
	 */
	public static void addHeaders(String headers) {
	    if (headers != null && !headers.isEmpty()) {
	        String[] headerArray = headers.split("&");
	        for (String header : headerArray) {
	            request.header(header.split("=")[0], header.split("=")[1]);
	        }
	    }
	}

	/**
	 * Adds parameters to the RestAssured request.
	 *
	 * @param parameters A string containing parameters and their values, separated by "&".
	 */
	public static void addParameters(String parameters) {
	    if (parameters != null && !parameters.isEmpty()) {
	        String[] parameterArray = parameters.split("&");
	        for (String parameter : parameterArray) {
	            request.queryParam(parameter.split("=")[0], parameter.split("=")[1]);
	        }
	    }
	}

	/**
	 * Sends a GET request using RestAssured.
	 *
	 * @param url        The request URL.
	 * @param headers    The request headers.
	 * @param parameters The request parameters.
	 * @return The response of the GET request.
	 */
	public static Response getRequest(String url, String headers, String parameters) {
	    addHeaders(headers);
	    addParameters(parameters);
	    return request.get(url);
	}

	/**
	 * Sends an API request and returns the response.
	 *
	 * @param url        The request URL.
	 * @param headers    The request headers.
	 * @param method     The request method (GET/POST).
	 * @param parameters The request parameters.
	 * @param body       The request body (for POST requests).
	 * @return The API response.
	 */
	public static Response makeApiRequest(String url, String headers, String method, String parameters, String body) {
	    ExtentTestManager.getTest().log(LogStatus.INFO, "Endpoint URL: " + url);
	    try {
	        switch (method.toLowerCase()) {
	            case "get":
	                request = RestAssured.given();
	                return getRequest(url, headers, parameters);
	            case "post":
	                // Implement postRequest method when needed. 
	                // return postRequest(url, headers, parameters, body);
	                break;
	            default:
	                throw new Exception("Invalid Method");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	/**
     * Validates the HTTP status code from the API response.
     * 
     * @param response The Response object.
     * @param expectedStatusCode The expected HTTP status code.
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        // Get the actual status code from the response
        int actualStatusCode = response.getStatusCode();
        
        // Log the status code (optional, for Extent Reports)
        ExtentTestManager.getTest().log(LogStatus.INFO, "Status Code: " + actualStatusCode);

        // Assert that the status code matches the expected status code
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Expected status code " + expectedStatusCode + " but got " + actualStatusCode);
    }

    /**
     * Validates that the response body is not empty.
     * 
     * @param response The Response object.
     */
    public static void validateResponseBodyNotEmpty(Response response) {
        // Get the response body as a string
        String body = response.getBody().asString();
        
        // Assert that the body is not empty
        Assert.assertFalse(body.isEmpty(), "Response body is empty");
    }
    
    /**
     * Validates a JSON value by comparing actual response with expected values.
     * Logs results in Extent Reports and adds soft assertions.
     *
     * @param attribute     The name of the attribute being validated.
     * @param jsonPath      The JSONPath to extract actual value.
     * @param jsonResponse  The JsonPath parsed response.
     * @param expectedValue The expected value from external data.
     */
    protected void validateJsonValue(String attribute, String jsonPath, Response jsonResponse, Object expectedValue) {
        // Initialize softAssert if it's null
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }

        // Get the actual value from the JSON response using JsonPath
        Object actualValue;
        try {
            actualValue = JsonPath.read(jsonResponse.asString(), jsonPath);

            // If JsonPath returns a list, check if any element matches expectedValue
            if (actualValue instanceof List<?> list && !list.isEmpty()) {
                boolean matchFound = list.stream().map(Object::toString).anyMatch(value -> value.trim().equals(expectedValue.toString().trim()));

                if (matchFound) {
                    actualValue = expectedValue; // Assign expected value to pass assertion
                } else {
                    actualValue = list; // Keep full list for better error reporting
                }
            }
        } catch (Exception e) {
            softAssert.fail("Failed to extract value for " + attribute + " using path: " + jsonPath);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Failed to extract value for " + attribute + " using JSONPath: " + jsonPath);
            return;
        }

        // Ensure actualValue is not null
        if (actualValue == null) {
            softAssert.fail("Value not found for: " + attribute);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Value not found for: " + attribute);
            return;
        }

        // Convert both actual and expected values to Strings for comparison
        String actualStr = String.valueOf(actualValue).trim();
        String expectedStr = String.valueOf(expectedValue).trim();

        // Perform assertion
        boolean isMatch = actualStr.equals(expectedStr);
        softAssert.assertEquals(actualStr, expectedStr, "Mismatch for attribute: " + attribute);

        // Log result to Extent Report
        if (isMatch) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Acceptance criteria matched for " + attribute + ". Expected: '" + expectedStr + "', Actual: '" + actualStr + "'");
        } else {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Acceptance criteria mismatch for " + attribute + ". Expected: '" + expectedStr + "', Actual: '" + actualStr + "'");
        }
    }	
}
