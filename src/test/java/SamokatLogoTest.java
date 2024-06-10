//доп.задание вне зачета

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjectPackage.HomePageYandexSamokat;

public class samokatLogoTest {
    private WebDriver driver;

    @Test
    public void samokatLogoTestChrome() {
        driver = new ChromeDriver();
        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHomePageURL());

        driver.findElement(By.xpath(".//img[@alt='Scooter']")).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,objHomePage.getHomePageURL());

    }

    @Test
    public void samokatLogoTestFirefox() {
        driver = new FirefoxDriver();
        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHomePageURL());

        driver.findElement(By.xpath(".//img[@alt='Scooter']")).click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL,objHomePage.getHomePageURL());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
