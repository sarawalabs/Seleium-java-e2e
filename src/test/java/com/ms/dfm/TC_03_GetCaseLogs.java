// Internal Title is getting updated for the Initial contact pending and Waiting for customer response.
// As of now we pull data for the latest case notes, however we don't check if the latest mail is received from the customer.
// ** Update Internal Title for all case statuses.
// ** we have to check the outlook/inbox with the case number if the customer has sent a mail.

package com.ms.dfm;


import java.io.IOException;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ms.dfm.modules.getCaseNotes;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import com.ms.dfm.modules.setupAndTearDown;

@SuppressWarnings("unused")
public class TC_03_GetCaseLogs extends TC_02_CollectCaseStatusAndSeverity {
	
	
	@Test(priority = 6, description = "Collect Latest Case Updates")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Collect Case Notes from each case, like latest case notes or latest mail from the customer")
	@Story("Colelct the Lates Case Notes")
	@Step("Collect CaseNotes")
	public void getLatestCaseNotes() throws IOException, InterruptedException {
		

		Reporter.log("======= Started capturing the latest case logs ====== ");
		
		test = extent.createTest("getLatestCaseNotes");

		test.log(Status.INFO, "Started Capturing the latest case Notes");
		
		getCaseNotes.caseNotes("Case_Details");

		test.log(Status.INFO, "Finished capturing the latest Case Notes");
		
		Reporter.log("Finished pulling the latest case logs");
	}
}
