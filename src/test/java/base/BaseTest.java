package base;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.ExtentManager;
import utils.ScreenshotUtils;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) {

        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(1000));

        page = browser.newPage();

        page.setDefaultTimeout(60000);
        page.setDefaultNavigationTimeout(60000);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        try {

            if (result.getStatus() == ITestResult.FAILURE) {

                test.fail(result.getThrowable());

                String screenshotPath =
                        ScreenshotUtils.captureScreenshot(
                                page,
                                result.getName());

                File file = new File(screenshotPath);

                System.out.println("Screenshot Path: "
                        + file.getAbsolutePath());

                System.out.println("File Exists: "
                        + file.exists());

                if (file.exists()) {

                    test.fail("Failure Screenshot")
                            .addScreenCaptureFromPath(
                                    file.getAbsolutePath());
                }

            } else if (result.getStatus() == ITestResult.SUCCESS) {

                test.pass("Test Passed");

            } else {

                test.skip("Test Skipped");
            }

        } catch (Exception e) {

            test.warning("Unable to attach screenshot: "
                    + e.getMessage());
        }

        extent.flush();

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}