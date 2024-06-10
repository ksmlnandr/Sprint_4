//доп.задание вне зачета

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePageYandexSamokat;

public class SamokatLogoTest {
    private WebDriver driver;

    @Test
    public void SamokatLogoTestChrome() {
        driver = new ChromeDriver();
        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHOME_PAGE_URL());

        objHomePage.clickScooterLogo();
        Assert.assertEquals(driver.getCurrentUrl(),objHomePage.getHOME_PAGE_URL());
    }

    @Test
    public void SamokatLogoTestFirefox() {
        driver = new FirefoxDriver();
        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHOME_PAGE_URL());

        objHomePage.clickScooterLogo();
        Assert.assertEquals(driver.getCurrentUrl(),objHomePage.getHOME_PAGE_URL());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
