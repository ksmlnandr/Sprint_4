package pageObjectPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageYandexSamokat {
    private WebDriver driver;

    private String homePageURL = "https://qa-scooter.praktikum-services.ru/";

    //локатор стрелки в блоке "Вопросы о важном"
    private String arrowButton = ".//div[@id='accordion__heading-%d']";

    // локатор области с текстом в блоке "Вопросы о важном"
    private String textArea = ".//div[@id='accordion__panel-%d']//p";

    //локатор кнопки заказа самоката
    private String orderButtonLink = ".//div[contains(@class,'%s')]/button[text()='Заказать']";

    //локатор поля Имя
    private By nameField = By.xpath(".//*[contains(@placeholder,'Имя')]");

    //локатор поля Фамилия
    private By surnameField = By.xpath(".//*[contains(@placeholder,'Фамилия')]");

    //локатор поля Адрес
    private By addressField = By.xpath(".//*[contains(@placeholder,'куда привезти')]");

    //локатор поля Телефон
    private By phoneField = By.xpath(".//*[contains(@placeholder,'Телефон')]");

    //локатор поля Метро
    private By subwayStationField = By.xpath(".//*[contains(@placeholder,'метро')]");

    private String subwayStationName = ".//div[text()='%s']";


    //локатор кнопки куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");

    public String getHomePageURL() {
        return homePageURL;
    }
    public String getArrowButton() {
        return arrowButton;
    }
    public String getTextArea() {
        return textArea;
    }
    public String getOrderButtonLink() { return orderButtonLink; }
    public String getSubwayStationName() { return subwayStationName; }



    //конструктор класса
    public HomePageYandexSamokat(WebDriver driver){
        this.driver = driver;
    }

    //метод, скрывающий баннер про куки
    public void hideCookieBanner() {
        driver.findElement(cookieButton).click();
    }

    public void fillUserData(String name, String surname, String address, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(subwayStationField).click();
    }

}

