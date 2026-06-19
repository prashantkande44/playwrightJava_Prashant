package utils;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class ScreenshotUtils {

    public static String captureScreenshot(Page page, String testName) {

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        String folderPath = "test-output/screenshots";

        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String screenshotPath = folderPath + File.separator
                + testName + "_" + timestamp + ".png";

        page.screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(screenshotPath))
                        .setFullPage(true));

        return screenshotPath;
    }
}