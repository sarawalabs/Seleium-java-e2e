package com.ms.dfm;

import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ms.dfm.modules.collectCaseData;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import com.ms.dfm.modules.setupAndTearDown;

@SuppressWarnings("unused")
public class TC_02_CollectCaseStatusAndSeverity extends setupAndTearDown{

	@Test(priority = 5, description = "Collect Open Case Info")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Collect Case General Information, like severity, case Number, Internal Title etc")
	@Story("Colelct the Case General Information")
	@Step("Collect Open Case Info")
	public void openCases() throws IOException, InterruptedException {

		Reporter.log("===== Checking the cases with open & Pending IR status ======", true);
		
		test = extent.createTest("openCases");

		test.log(Status.INFO, "Started Capturing the Opended Case details");
		
		collectCaseData.collectCaseInfo();

		test.log(Status.INFO, "Finished Capturing the Opened case Details");
		
		Reporter.log("===== Done Checking the cases with open & Pending IR status ======", true);

	}

}
