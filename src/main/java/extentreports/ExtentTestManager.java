package extentreports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages individual ExtentTest instances for each test thread.
 */
public class ExtentTestManager {
	private static final Map<Long, ExtentTest> testMap = new HashMap<>();
	private static ExtentReports extent = ExtentManager.getInstance();

	/**
	 * Retrieves the current test instance.
	 *
	 * @return Current thread's ExtentTest instance
	 */
	public static synchronized ExtentTest getTest() {
		return testMap.get(Thread.currentThread().getId());
	}

	/**
	 * Starts a new test and stores it in the test map.
	 *
	 * @param testName Name of the test
	 */
	public static synchronized void startTest(String testName) {
		ExtentTest test = extent.startTest(testName);
		testMap.put(Thread.currentThread().getId(), test);
	}

	/**
	 * Ends the current test and removes it from the test map.
	 */
	public static synchronized void endTest() {
		ExtentTest test = testMap.get(Thread.currentThread().getId());
		if (test != null) {
			extent.endTest(test);
			testMap.remove(Thread.currentThread().getId());
		}
	}
}
