package com.gmail.ivanytskyy.vitaliy.ui.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.gmail.ivanytskyy.vitaliy.ui.utils.WebDriverHolder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vitaliy Ivanytskyy
 * @version 1.01
 * @date 11/01/2024
 */
public class UIExtentReportsListener extends ExtentITestListenerClassAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(UIExtentReportsListener.class);
    static {
        System.setProperty("extent.reporter.html.start", "true");
        System.setProperty("extent.reporter.html.out", "target/extentReport/UIExtentHtml.html");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        LOG.error("Exception occurred: ", result.getThrowable());
        ExtentTest test = ExtentTestManager.getTest(result);
        File file = getScreenshot(result);
        try {
            test.addScreenCaptureFromBase64String(Base64
                    .getEncoder()
                    .encodeToString(FileUtils.readFileToByteArray(file)), "Failed test");
            file.delete();
        } catch (IOException e) {
            LOG.error("Exception occurred: ", e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
        ExtentTest test = ExtentTestManager.getTest(result);
        File file = getScreenshot(result);
        try {
            test.addScreenCaptureFromBase64String(Base64
                    .getEncoder()
                    .encodeToString(FileUtils.readFileToByteArray(file)), "Skipped test");
            file.delete();
        } catch (IOException e) {
            LOG.error("Exception occurred: ", e);
            throw new RuntimeException(e);
        }
    }
    protected File getScreenshot(ITestResult result){
        File fileForCopy = new File(result.getName() + ".jpg");
        File screenshotFile = ((TakesScreenshot) WebDriverHolder.getWebDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, fileForCopy);
        } catch (IOException e) {
            LOG.error("Exception occurred: ", e);
            throw new RuntimeException(e);
        }
        return fileForCopy;
    }
}