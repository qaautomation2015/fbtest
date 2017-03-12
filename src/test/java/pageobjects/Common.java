package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {
    public static void waitForLoad(WebDriver driver){
        new WebDriverWait(driver,60).until((ExpectedCondition<Boolean>) wd->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitElementVisible(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public static void waitElementClickable(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitElementAbsence(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(element)));
    }

    public static void waitElementPresence(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

}
