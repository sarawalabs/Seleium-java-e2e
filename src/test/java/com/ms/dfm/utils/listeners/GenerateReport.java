package com.ms.dfm.utils.listeners;

import java.io.File;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ms.dfm.modules.runAllureCommands;

public class GenerateReport {
	
	public static ExtentReports extent;
	
	public static ExtentSparkReporter spark;
	
	public static ExtentTest test;
	
	@BeforeSuite(alwaysRun = true)
	public static void initiateReporter() throws IOException {
		final File CONF = new File("./reports/spark-config.json");
		
		extent = new ExtentReports();
		
		spark = new ExtentSparkReporter("./reports/Execution_Reports.html");
		
		spark.loadJSONConfig(CONF);
		extent.attachReporter(spark);
	}
	
	@AfterMethod(alwaysRun = true) 
	public static void getResult(ITestResult result) {
		
		if(result.getStatus() == ITestResult.SUCCESS){
			test.pass(MarkupHelper.createLabel(result.getName() + " Passed ", ExtentColor.GREEN));
		}
		
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			test.fail(MarkupHelper.createLabel(result.getName() + " Failed ", ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		
		else if(result.getStatus() == ITestResult.SKIP){
			test.skip(MarkupHelper.createLabel(result.getName() + " Skipped ", ExtentColor.YELLOW));
		}
		
	}
	
	@AfterSuite
	public static void closeReporter() throws IOException {
		
		extent.flush();
		
		//runAllureCommands.generateReport();
	}

}
