package tests;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageobjects.MainPage;
import pageobjects.UserProfilePage;
import utils.Data;

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JUnitChromeTest {
    private static WebDriver driver;
    private static String baseUrl;
    private static StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void setup() throws AWTException {
        System.setProperty("webdriver.chrome.driver", Data.DRIVER_PATH + "chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = Data.TESTURL;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Ignore
    @Test
    public void aaTestRemoveAllPosts() throws InterruptedException, AWTException {
        MainPage.loginUser(driver, "test.automation.qa2015@gmail.com", "2015user02", "Testov Automa");
        driver.findElement(By.xpath(UserProfilePage.UPROFILE_MENU)).click();
        UserProfilePage.removePosts(driver,30);
    }

    @Test
    public void aTestPostMessageTest() throws InterruptedException, AWTException {
        MainPage.loginUser(driver, "test.automation.qa2015@gmail.com", "2015user02", "Testov Automa");
        MainPage.postMessage(driver, Data.postedMessage);
    }

    @Test
    public void bTestCheckPostedMessageOnProfilePage() throws InterruptedException, AWTException {
        UserProfilePage.open(driver);
        Assert.assertTrue(driver.findElement(By.xpath(UserProfilePage.UPROFILE_FIRST_POST_TEXT)).getText().contains(Data.postedMessage));
    }

    @Test
    public void cTestCheckPostedMessageByFriend() throws InterruptedException, AWTException {
        MainPage.signOut(driver);
        MainPage.loginUser(driver, "barakobama2017@gmail.com", "2015user02", "QaBarak ObamaTest");
        UserProfilePage.open(driver);
        UserProfilePage.openFriendPage(driver, "Testov Automa");
        Assert.assertTrue(driver.findElement(By.xpath(UserProfilePage.UPROFILE_FIRST_POST_TEXT)).getText().contains(Data.postedMessage));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Data.SCREENSHOTS_PATH + "screenshot.png"));
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}