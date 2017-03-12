package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import pageobjects.Common;
import pageobjects.MainPage;
import pageobjects.UserProfilePage;
import setup.SetupForTNG;
import utils.Data;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.Assert.fail;

public class TNGTest {

    private static WebDriver driver;
    private static String browserName;
    private static StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    @Parameters({"browser"})
    public void setUp(String browser) throws MalformedURLException {
        driver = SetupForTNG.getDriver(browser);
        browserName = browser;
    }

    @Test
    public void aTestPostMessageTest() throws InterruptedException, AWTException {
        MainPage.loginUser(driver, "test.automation.qa2015@gmail.com", "2015user02", "Testov Automa");
        MainPage.postMessage(driver, Data.postedMessage + " from " + browserName);
    }

    @Test
    public void bTestCheckPostedMessageOnProfilePage() throws InterruptedException, AWTException {
        UserProfilePage.open(driver);
        Common.waitElementPresence(driver,By.xpath(UserProfilePage.UPROFILE_FIRST_POST_TEXT));//necessary for FF
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
    public static void tearDown() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Data.SCREENSHOTS_PATH + "screenshot.png"));
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }

    }

}
