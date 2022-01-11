package com.ms.dfm;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ms.dfm.modules.selectMailTemplates;
import com.ms.dfm.modules.setupAndTearDown;

import java.io.IOException;
import org.testng.Reporter;

/*When you send a mail it is required to update the case notes, labor and at some times case status. 
It is been done through the TC_09_AddCaseNotesLabor class*/

public class TC_04_SendMail extends TC_03_GetCaseLogs {

	@Test(priority = 7)
	public void createMails() throws IOException, InterruptedException {

		Reporter.log("===== Send mail Started ======", true);
		
		test = extent.createTest("createMails");
		
		test.log(Status.INFO, "Started Sending the mails");
		
		selectMailTemplates.selectMailTemplate();
		
		test.log(Status.INFO, "Finished Sending the mails");

		Reporter.log("===== Send mail Finished ======", true);
	}

}
