import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

public class BaseClass {
    @BeforeTest
    public void setUp(){
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
    }

    @AfterTest
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    @AfterMethod
    public void methodEnd(){
        Selenide.webdriver().driver().close();
    }
}
