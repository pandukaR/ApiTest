package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;

import extentreports.ExtentManager;
import extentreports.ExtentTestManager;

/**
 * TestNG Listener for managing Extent Reports life cycle. Automatically logs
 * test start, success, failure, and skip events.
 */
public class ExtentReportListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped: " + result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		ExtentManager.getInstance().flush();
	}
}
