package extentreports;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * Manages the singleton instance of ExtentReports. Ensures only one instance of
 * ExtentReports is created and used throughout the execution.
 */
public class ExtentManager {
	private static ExtentReports extent;

	/**
	 * Provides a synchronized instance of ExtentReports. If an instance doesn't
	 * exist, it creates one.
	 *
	 * @return Singleton instance of ExtentReports
	 */
	public static synchronized ExtentReports getInstance() {
		if (extent == null) {
			extent = new ExtentReports("report-output/ExtentReport.html", true);
		}
		return extent;
	}
}
