import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjectPackage.HomePageYandexSamokat;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class orderFlowTest {
    private WebDriver driver;

    private final String orderButtonClass;
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStationName;
    private final String phone;
    private final String day;
    private final String duration;
    private final String colour;
    private final String comment;

    public orderFlowTest(String orderButtonClass,
                                 String name, String surname, String address, String subwayStationName, String phone,
                                 String day, String duration, String colour, String comment) {
        this.orderButtonClass = orderButtonClass;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStationName = subwayStationName;
        this.phone = phone;

        this.day = day;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] setUserData() {
        return new Object[][] {
                {"Header_Nav",
                        "Иван", "Васильев", "Москва, ул. Тверская, д. 1", "Сокольники", "+78005553535",
                        "20", "сутки", "black", "Оставьте у двери"},
                {"Home_FinishButton__1_cWm",
                        "Мария", "Петрова", "Ижевск, ул. Ленина, д. 34", "Чистые пруды", "+7900000001",
                        "24", "двое суток", "grey", "Позвоните перед приездом"},
        };
    }

    @Test
    public void orderFlowTestChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHomePageURL());
        objHomePage.hideCookieBanner();

        Assert.assertTrue(driver.findElement(By.xpath(String.format(objHomePage.getOrderButtonLink(),orderButtonClass))).isEnabled()); //класс кнопки заказа в шапке
        driver.findElement(By.xpath(String.format(objHomePage.getOrderButtonLink(),orderButtonClass))).click();

        Assert.assertTrue(driver.findElement(By.className("Order_Form__17u6u")).isDisplayed());
        objHomePage.fillUserData(name, surname, address, phone);
        driver.findElement(By.xpath(String.format(objHomePage.getSubwayStationName(),subwayStationName))).click();
        driver.findElement(By.xpath(".//button[text()='Далее']")).click();

        Assert.assertTrue(driver.findElement(By.className("Order_Form__17u6u")).isDisplayed());
        driver.findElement(By.xpath(".//*[contains(@placeholder,'Когда привезти')]")).click();
        driver.findElement(By.xpath(String.format(".//div[@role='button' and text()='%s']",day))).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-root']")).click();
        driver.findElement(By.xpath(String.format(".//div[@class='Dropdown-option' and text()='%s']",duration))).click();
        driver.findElement(By.id(colour)).click();
        driver.findElement(By.xpath(".//*[contains(@placeholder,'Комментарий')]")).sendKeys(comment);
        driver.findElement(By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".Order_ModalHeader__3FDaJ")).isDisplayed());
        driver.findElement(By.xpath(".//button[text()='Да']")).click();

        Assert.assertTrue(driver.findElement(By.xpath(".//div[text()='Заказ оформлен']")).isDisplayed());
        driver.findElement(By.xpath(".//button[text()='Посмотреть статус']")).click();
    }

    @Test
    public void orderFlowTestFirefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHomePageURL());
        objHomePage.hideCookieBanner();

        Assert.assertTrue(driver.findElement(By.xpath(String.format(objHomePage.getOrderButtonLink(),orderButtonClass))).isEnabled()); //класс кнопки заказа в шапке
        driver.findElement(By.xpath(String.format(objHomePage.getOrderButtonLink(),orderButtonClass))).click();

        Assert.assertTrue(driver.findElement(By.className("Order_Form__17u6u")).isDisplayed());
        objHomePage.fillUserData(name, surname, address, phone);
        driver.findElement(By.xpath(String.format(objHomePage.getSubwayStationName(),subwayStationName))).click();
        driver.findElement(By.xpath(".//button[text()='Далее']")).click();

        Assert.assertTrue(driver.findElement(By.className("Order_Form__17u6u")).isDisplayed());
        driver.findElement(By.xpath(".//*[contains(@placeholder,'Когда привезти')]")).click();
        driver.findElement(By.xpath(String.format(".//div[@role='button' and text()='%s']",day))).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-root']")).click();
        driver.findElement(By.xpath(String.format(".//div[@class='Dropdown-option' and text()='%s']",duration))).click();
        driver.findElement(By.id(colour)).click();
        driver.findElement(By.xpath(".//*[contains(@placeholder,'Комментарий')]")).sendKeys(comment);
        driver.findElement(By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".Order_ModalHeader__3FDaJ")).isDisplayed());
        driver.findElement(By.xpath(".//button[text()='Да']")).click();

        Assert.assertTrue(driver.findElement(By.xpath(".//div[text()='Заказ оформлен']")).isDisplayed());
        driver.findElement(By.xpath(".//button[text()='Посмотреть статус']")).click();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
