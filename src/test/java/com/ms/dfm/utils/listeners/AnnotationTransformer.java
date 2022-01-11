package com.ms.dfm.utils.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.internal.annotations.ITest;

public class AnnotationTransformer implements IAnnotationTransformer {

	public void Transform(ITest annotation, Class testClass, Constructor testConstructor, Method testMethod) {

		// To enable retry the failed tests.
		annotation.setRetryAnalyzer(RetryFailedTests.class);

// Did not work on changing the annotation at the runtime from "closeBrowser" in "setupAndTearDown" 
//and "verifyHomepage" in "TC_01_DFM_Login"

//		if (isTestConfigured(annotation)) {
//
//			annotation.setEnabled(false);
//		}
//
//	}
//
//	public boolean isTestConfigured(ITestAnnotation a) {
//
//		if (a.getAlwaysRun()) {
//			return true;
//		}
//		return false;
//	}

	}
}
