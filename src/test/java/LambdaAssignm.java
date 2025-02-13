import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;

public class LambdaAssignm {
    //public WebDriver driver;
    public RemoteWebDriver driver;
    private final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    @BeforeTest
    public void setupBrowser() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        //driver = new ChromeDriver(options);
        //driver.manage().window().maximize();

        String hub = "@hub.lambdatest.com/wd/hub";
        String username = "gutteranjitsinh99";
        String accessKey = "LT_WBUpw96w5ci4MPK96IW9iuBnJLRhJB85V7ZEaYV76J8He09";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("130");

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", accessKey);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("network", true);
        ltOptions.put("project", "LambdaTestFinalKrishn");
        ltOptions.put("tunnel", true);
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);




        driver = new RemoteWebDriver(new URL("https://"+ username + ":" + accessKey +hub), browserOptions);
        //driver = new RemoteWebDriver(new URL(hub), browserOptions);

    }

    @BeforeMethod
    public void openBaseUrl() {
        driver.get("https://www.lambdatest.com/selenium-playground/");
    }

    @Test(timeOut = 20000)
    public void validatePageTitleAndLogo() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        softAssert.assertEquals(driver.getTitle(), "LambdaTest", "expecting a failure and proceeding to the\n" +
                "following statement");
        WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='Logo']")));
        softAssert.assertTrue(logoElement.isDisplayed(), "Logo is not displayed!");

        softAssert.assertAll();
        System.out.println("Expected failure for title assertion");
    }

    @Test(timeOut = 20000)
    public void checkboxDemoTest() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkbox Demo"))).click();
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("isAgeSelected")));

        checkbox.click();
        softAssert.assertTrue(checkbox.isSelected(), "Checkbox is not selected!");

        checkbox.click();
        softAssert.assertFalse(checkbox.isSelected(), "Checkbox is still selected!");

        softAssert.assertAll();
    }

    @Test(timeOut = 20000)
    public void testJavascriptAlert() {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Javascript Alerts"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Click Me')]"))).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        softAssert.assertEquals(alert.getText(), "I am an alert box!", "Alert message does not match!");
        alert.accept();

        softAssert.assertAll();
    }



    @AfterMethod
    public void captureFailureScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);

            // Make sure the directory exists before saving the screenshot
            File screenshotDir = new File("screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs(); // Create the directory if it doesn't exist
            }

            // Save the screenshot
            try {
                FileUtils.copyFile(screenshot, new File(screenshotDir + "/" + result.getName() + ".png"));
                System.out.println("Screenshot captured for test case: " + result.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
