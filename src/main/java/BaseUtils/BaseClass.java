package BaseUtils;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	ExtentReports extent;
	public static ExtentTest test;
	protected static String baseURI;

	@BeforeTest
	@Parameters({ "url" })
	public static void initTest(String url) {
		baseURI = url;

	}

	@BeforeSuite
	public void getReportObject() {

		String path = System.getProperty("user.dir") + "/Input/Report/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Api Test");
		reporter.config().setReportName("This is api automation");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setCss(
				".name{letter-spacing: 2px; text-transform: uppercase; font-family: cursive;}");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@BeforeMethod
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@AfterMethod
	public void onTestCompletion(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Passed");
		} else {
			test.fail(result.getThrowable());
			test.log(Status.FAIL, "Failed");
		}
	}

	@AfterSuite
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
