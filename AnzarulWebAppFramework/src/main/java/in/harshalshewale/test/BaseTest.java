package in.harshalshewale.test;

import org.junit.jupiter.api.Test;

public abstract class BaseTest {

	String testId;
	String testSuiteName;
	String testCaseName;
	String testDescription;

	public BaseTest(String testId, String testSuiteName, String testCaseName, String testDescription) {

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

}
