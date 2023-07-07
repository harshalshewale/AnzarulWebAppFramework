package in.harshalshewale.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import in.harshalshewale.util.FileUtil;

public abstract class BaseTest {

	int testId;
	String testSuiteName;
	String testCaseName;
	String testDescription;

	public WebDriver driver;

	public BaseTest(int testId, String testSuiteName, String testCaseName, String testDescription) {

		this.testId = testId;
		this.testSuiteName = testSuiteName;
		this.testCaseName = testCaseName;
		this.testDescription = testDescription;
	}

	public abstract void runTest() throws Exception;

	@Test
	public final void runTestMaster() throws Exception {

		System.out.println("Im in the RunTestMaster");

		runTest();
	}

	protected WebDriver getWebDriver() throws Exception {

		// Read Browser name from test.properties
		String browser = null;
		browser = FileUtil.readPropertyFile("test", "browser");
		System.out.println("Selected Browser : " + browser);

		// Initialize Browser
		if (browser.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();

		}

		return driver;

	}

}
