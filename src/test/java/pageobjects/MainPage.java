package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Data;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainPage {
    final static String MENU_SIGNOUT_XPATH = "//div[@id='userNavigationLabel']";
    final static String SIGNOUT_XPATH = "//li[contains(@data-gt,'menu_logout')]";

    final static String STATUS_POST_XPATH1 = "//div[@id='feedx_sprouts_container']/descendant::textarea";
    final static String STATUS_POST_XPATH2 = "//div[@id='feedx_sprouts_container']/descendant::div[@contenteditable='true']/div[1]/div[1]/div[1]";

    @FindBy(xpath = "//div[@id='feedx_sprouts_container']/descendant::button[@type='submit']")
    private static WebElement postMessageButton;

    final static String STATUS_POST_POPUP_XPATH = "//div[@id='feedx_sprouts_container']/descendant::div[@contenteditable='true']";
    final static String STATUS_POST_BUTTON_XPATH = "//div[@id='feedx_sprouts_container']/descendant::button[@type='submit']";

    public static void loginUser(WebDriver driver, String userName, String userPassword,String userTitle) throws AWTException, InterruptedException {
        driver.get(Data.TESTURL);
        driver.findElement(By.id("email")).sendKeys(userName);
        driver.findElement(By.id("pass")).sendKeys(userPassword);
        driver.findElement(By.id("loginbutton")).click();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
//        Assert.assertTrue(driver.findElement(By.cssSelector(".fbxWelcomeBoxName")).getText().contains(userTitle));
    }

    public static void postMessage(WebDriver driver, String messageText) throws InterruptedException {
        try{
            driver.findElement(By.xpath(STATUS_POST_XPATH1)).click();
        }catch (Exception e){
            driver.findElement(By.xpath(STATUS_POST_XPATH2)).click();
        }
        driver.findElement(By.xpath(STATUS_POST_POPUP_XPATH)).sendKeys(messageText);
//        postMessageButton.click();
        driver.findElement(By.xpath(STATUS_POST_BUTTON_XPATH)).click();
//        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath(UserProfilePage.UPROFILE_FIRST_POST_TEXT)).getText().contains(messageText));
    }

    public static void signOut(WebDriver driver){
        driver.findElement(By.xpath(MENU_SIGNOUT_XPATH)).click();
        driver.findElement(By.xpath(SIGNOUT_XPATH)).click();
    }
//
}