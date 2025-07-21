package com.comcast.crm.listenerutility;


import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.basetest.BaseClass;

public class ListImpClass implements ITestListener , ISuiteListener {

	public ExtentReports report;
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		//ISuiteListener.super.onStart(suite);
		System.out.println("Report configuration");
		String time=new Date().toString().replace(" ", "_").replace(":", "_");

		
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM suite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);

		// add Env information &create
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		//ISuiteListener.super.onFinish(suite);
		System.out.println("Report backUp");
		report.flush();

	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		System.out.println("====>"+result.getMethod().getMethodName()+"<===START==");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
        test.log(Status.INFO, result.getMethod().getMethodName()+"==> STATED <==");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		System.out.println("====>"+result.getMethod().getMethodName()+"<===END==");
        test.log(Status.PASS, result.getMethod().getMethodName()+"==> COMPLETED <==");


	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailure(result);
		
		String testName=result.getMethod().getMethodName();
		
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
        test.log(Status.FAIL, result.getMethod().getMethodName()+"==> FAILED <==");

		
//		try {
//			File des=new File("./screenshots/"+ "+"+time+testName +".png");
//			org.openqa.selenium.io.FileHandler.copy(tem, des);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
	}	
	
}
