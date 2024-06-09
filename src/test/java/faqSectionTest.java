import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjectPackage.HomePageYandexSamokat;

@RunWith(Parameterized.class)
public class faqSectionTest {
    private WebDriver driver;

    private final int faqIndex;
    private final String expectedAnswer;

    public faqSectionTest(int faqIndex, String expectedAnswer) {
        this.faqIndex = faqIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] faqSectionTestData() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }


    @Test
    public void faqSectionTestChrome() {
        //я хотел сделать инициализацию драйвера через параметризацию, но не нашел как это сделать,
        // а наставник сказал, что и не надо ее делать, так как такого явного требования не было

        driver = new ChromeDriver();
        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHomePageURL());

        String exactArrowButton = String.format((objHomePage.getArrowButton()),faqIndex);
        String exactTextArea = String.format((objHomePage.getTextArea()),faqIndex);

        WebElement element = driver.findElement(By.xpath(exactArrowButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();

        String answer = driver.findElement(By.xpath(exactTextArea)).getText();
        Assert.assertTrue(answer != null);
        Assert.assertEquals(answer, expectedAnswer);
    }

    @Test
    public void faqSectionTestFirefox() {
        driver = new FirefoxDriver();
        HomePageYandexSamokat objHomePage = new HomePageYandexSamokat(driver);

        driver.get(objHomePage.getHomePageURL());

        String exactArrowButton = String.format((objHomePage.getArrowButton()),faqIndex);
        String exactTextArea = String.format((objHomePage.getTextArea()),faqIndex);

        WebElement element = driver.findElement(By.xpath(exactArrowButton));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();

        String answer = driver.findElement(By.xpath(exactTextArea)).getText();
        Assert.assertTrue(answer != null);
        Assert.assertEquals(answer, expectedAnswer);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
