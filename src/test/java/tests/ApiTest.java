package tests;

import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;

import extentreports.ExtentTestManager;

import io.restassured.response.Response;
import utils.BaseApi;
import utils.ConfigReader;
import utils.JsonUtils;

/**
 * This class contains the API tests and extends the BaseApiTest class to reuse
 * common functionality.
 */
public class ApiTest extends BaseApi {
	private SoftAssert softAssert;

	/**
	 * Sets up the Extent Report and initializes SoftAssert before test execution.
	 */
	@BeforeClass
	public void setupExtentReport() {
		ExtentTestManager.startTest("Assurity Assignment API Test");
		softAssert = new SoftAssert();
	}

	/**
	 * Validates the API response for a GET request.
	 */
	@Test
	public void validateApiResponse() {
		ExtentTestManager.getTest().log(LogStatus.INFO, "Starting API test to validate the response for GET request");

		// Retrieve the API URL from the properties file
		String apiUrl = ConfigReader.getProperty("apiUrl");

		// Send API request and store the response
		Response response = BaseApi.makeApiRequest(apiUrl, "", "GET", "", "");

		// Validate status code
		validateStatusCode(response, 200);

		// Validate response body is not empty
		validateResponseBodyNotEmpty(response);

		// Fetch expected values from external JSON
		Map<String, Object> expectedValues = JsonUtils.getExpectedValues();

		// Perform assertions using JSON paths
		validateJsonValue("name", "$.Name", response, expectedValues.get("name"));
		validateJsonValue("canRelist", "$.CanRelist", response, expectedValues.get("canRelist"));
		validateJsonValue("galleryPromotionDescription", "$.Promotions[?(@.Name == 'Gallery')].Description", response,
				expectedValues.get("galleryPromotionDescription"));

		// Ensure all soft assertions are executed before test fails
		softAssert.assertAll();

		ExtentTestManager.getTest().log(LogStatus.INFO, "API response validation completed ");
	}

	/**
	 * Ends the test execution and logs final results.
	 */
	@AfterMethod
	public void tearDown() {
		ExtentTestManager.endTest();
	}

}
