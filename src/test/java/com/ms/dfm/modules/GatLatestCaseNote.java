package com.ms.dfm.modules;

import java.io.IOException;
import java.util.ArrayList;


public class GatLatestCaseNote extends setupAndTearDown {

	public static String caseNotes;

	public static ArrayList<String> CaseNotes;

	public static ArrayList<String> copyLatestCaseNotes(String cxName) throws IOException, InterruptedException {

		CaseNotes = CaseTimelineActions.mailTimeline(cxName);
		
		return CaseNotes;
	}

}
