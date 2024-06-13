import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.HomePageYandexSamokat;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderFlowTest {
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

    public OrderFlowTest(String orderButtonClass,
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
                {HomePageYandexSamokat.getOrderButtonHeader(),
                        "Иван", "Васильев", "Москва, ул. Тверская, д. 1", "Сокольники", "+78005553535",
                        "20", "сутки", "black", "Оставьте у двери"},
                {HomePageYandexSamokat.getOrderButtonBody(),
                        "Мария", "Петрова", "Ижевск, ул. Ленина, д. 34", "Чистые пруды", "+7900000001",
                        "24", "двое суток", "grey", "Позвоните перед приездом"},
        };
    }

    @Test
    public void orderFlowTestChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHOME_PAGE_URL());
        objHomePage.hideCookieBanner();

        objHomePage.locateAndClickOrderButton(orderButtonClass);

        objHomePage.fillUserData(name, surname, address, subwayStationName, phone);
        objHomePage.clickNextButton();

        objHomePage.fillDeliveryDetails(day, duration, colour, comment);

        objHomePage.checkConfimationModalFormIsVisible();

        objHomePage.checkSuccesfulOrderFormIsVisible();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
