import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TestGoogleClass extends BaseClass{
    private final static String url = "https://www.google.ru";
    @Test(description = "test1 to execute")
    public void testLuckyButton(){
        openUrl(url);
        $(byXpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]")).click();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://www.google.com/doodles");
    }
    @Test(description = "test2 to execute")
    public void testConditions(){
        openUrl(url);
        $(byLinkText("Условия")).click();
        Assert.assertTrue($(byId("sdgBod")).text().contains("Политика конфиденциальности"));
    }
    @Test(description = "test3 to execute")
    public void testSearch(){
        openUrl(url);
        $(byName("q")).val("pigs").pressEnter();
        ElementsCollection elements = $$(byCssSelector(".LC20lb"));
        Assert.assertEquals(elements.size(), 17);
    }
}
