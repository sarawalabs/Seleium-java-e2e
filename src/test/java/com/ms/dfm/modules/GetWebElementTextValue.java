package com.ms.dfm.modules;

// This class returns the SR number from the list data copied from the open case UL (unordered list) element from main page

// This code can be used for list of Open cases, List of IR Pending cases, It updates below 3 items.

// 1. Case Number
// 2. Case Severity
// 3. Case Status


public class GetWebElementTextValue {

	public static String extractSR(String str) {

		String [] Split1 = str.split("\n", 3);
		
		String [] Split2 = Split1[1].split(" ", 2);
		
		String SR = Split2[0];

		return SR;
	}

	public static String getCustomerName(String str) {

		String [] Split1 = str.split("\n", 4);
		
		String customerName = Split1[2];

		return customerName;
	}
	
	public static String getSeverity(String str) {

		String Severity = str.substring(0, 1);

		return Severity;
	}

	public static String getCaseStatus(String str) {

		// Case status will have a new line in the beginning, therefore a new line has
		// to be removed from the first position.
		String caseStatus = str.substring(str.lastIndexOf("\n"));

		int end = caseStatus.length();

		caseStatus = caseStatus.substring(1, end);

		return caseStatus;
	}

	public static String getCaseNotes(String str) {
		
		String caseNotes = str.replace("\n", "");
		
		if(caseNotes.length()>32767) {
			
			return caseNotes.substring(0, 32765);
		}

		else {
			return caseNotes;
		}
	}
	
	public static String getCaseNumber(String str) {
		
		String[] result = str.split("\n",2);
		
		return result[0];
	}

}
