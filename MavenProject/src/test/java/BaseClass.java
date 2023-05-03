import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import static com.codeborne.selenide.Selenide.open;

public class BaseClass {
    @BeforeTest
    public void setUp(){
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
    }
    public void openUrl(String site_url){
        open(site_url);
    }
    @AfterTest
    public void theEnd(){
        Selenide.closeWebDriver();
    }
}
