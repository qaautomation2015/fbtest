package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Data;

public class UserProfilePage {
    public final static String UPROFILE_MENU = "//div[@role='navigation']/div[1]/div[1]/div[1]/a[1]/span";
    public final static String UPROFILE_FRIENDS_LINK = "//a[@data-tab-key='friends']";
    public final static String UPROFILE_FIRST_POST_TEXT = "(//div[@role='article'])[1]/div[1]/div[3]/div[2]/div";
    public final static String UPROFILE_FIRST_POST_MENU = "(//div[@role='article']/div[1]/div[1]/div[1]/a)[1]";
    public final static String UPROFILE_DELETE_POST_LINK = "//li[@data-feed-option-name='FeedDeleteOption']";
    public final static String UPROFILE_DELETE_POST_CONFIRM = "//div[@role='dialog']/descendant::button[@type='submit']";


    public static void open(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath(UserProfilePage.UPROFILE_MENU)).click();
    }

    public static void openFriendPage(WebDriver driver, String friendName) throws InterruptedException {
        driver.findElement(By.xpath(UserProfilePage.UPROFILE_FRIENDS_LINK)).click();
        driver.findElement(By.xpath("//div[@class='uiProfileBlockContent']/descendant::a[contains(text(),'" + friendName + "')]")).click();
    }

    public static void removePosts(WebDriver driver, int number) throws InterruptedException{
        String postText;
        try {
            for (int i = 1; i < number; i++) {
                postText = driver.findElement(By.xpath(UPROFILE_FIRST_POST_TEXT)).getText();
                driver.findElement(By.xpath(UPROFILE_FIRST_POST_MENU)).click();
                driver.findElement(By.xpath(UPROFILE_DELETE_POST_LINK)).click();
                Common.waitForLoad(driver);
                Thread.sleep(5000);
                driver.findElement(By.xpath(UPROFILE_DELETE_POST_CONFIRM)).click();
                System.out.println("Post removed :" + postText);
                driver.get(Data.TESTURL);
                driver.findElement(By.xpath(UserProfilePage.UPROFILE_MENU)).click();
            }
        } catch (Exception e) {
            System.out.println("All posts were removed");
        }
    }
}