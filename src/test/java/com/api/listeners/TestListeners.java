package com.api.listeners;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListeners.class);

    public void onTestStart(ITestResult result) {
        logger.info("Test Case execution started "+result.getMethod().getMethodName());
    }


    public void onTestSuccess(ITestResult result) {
        logger.info("Test Case Passed "+result.getMethod().getMethodName());
        logger.info("Description "+result.getMethod().getDescription());
    }


    public void onTestFailure(ITestResult result) {
        logger.error("Test Case Failed "+result.getMethod().getMethodName());
        logger.info("Description "+result.getMethod().getDescription());
    }

    public void onTestSkipped(ITestResult result) {
        logger.info("Test Case Skipped "+result.getMethod().getMethodName());
        logger.info("Description "+result.getMethod().getDescription());
    }

    public void onStart(ITestContext context) {
        logger.info("Test Suite execution started....");
    }


    public void onFinish(ITestContext context) {
        logger.info("Test Suite execution finished....");
    }
}
