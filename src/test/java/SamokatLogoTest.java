//доп.задание вне зачета

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePageYandexSamokat;

public class SamokatLogoTest {
    private WebDriver driver;

    @Test
    public void samokatLogoTestChrome() {
        driver = new ChromeDriver();
        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHOME_PAGE_URL());

        objHomePage.clickScooterLogo();
        Assert.assertEquals(objHomePage.getHOME_PAGE_URL(), driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
