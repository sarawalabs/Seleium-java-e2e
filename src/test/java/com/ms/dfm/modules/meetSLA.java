package com.ms.dfm.modules;

import java.io.IOException;


public class meetSLA extends setupAndTearDown {
	
	
	public static void meetSLA_Call(String caseNumber) throws IOException, InterruptedException{
		
		@SuppressWarnings("unused")
		searchCases  search = new searchCases(caseNumber);
		
		plusButtonAction.buttonActions("phoneCall", "");
		
		/* Since meeting SLA will not change the state from Initial Contact Pending to 'waiting for Information'
		    Adding the additional method here to change the status
		 */
		
		ChangeCaseStatuses.changeStatus("Pending customer response");
		
	}

	public static void meetSLA_mail() throws IOException, InterruptedException {
		
		
		
		/* Since meeting SLA will not change the state from Initial Contact Pending to 'waiting for Information'
	    Adding the additional method here to change the status
	 */
		
		ChangeCaseStatuses.changeStatus("Pending customer response");
	}
}
