package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import utils.Data;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class SetupForTNG {

    static WebDriver driver;
    private static String baseUrl = Data.TESTURL;

    public static WebDriver getDriver(String browser) throws MalformedURLException {

        switch (browser) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", Data.DRIVER_PATH + "chromedriver.exe");
                driver = new ChromeDriver();
                driver.get(baseUrl);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    driver.quit();
                }
            }
            break;

            case "firefox": {
                driver = new FirefoxDriver();
                driver.get(baseUrl);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    driver.quit();
                }
            }
            break;

            case "ie11": {
                System.setProperty("webdriver.ie.driver", Data.DRIVER_PATH + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                driver.get(baseUrl);
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    driver.quit();
                }
            }
            break;
        }
        return driver;
    }
}
