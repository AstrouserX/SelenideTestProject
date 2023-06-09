import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationClass extends BaseClass{
    private final static String url = "https://the-internet.herokuapp.com/basic_auth";
    private final static String username = "admin";
    private final static String password = "admin";
    private final static String expected_value = "Congratulations! You must have the proper credentials.";
    @Test
    public void authorizationTest() throws InterruptedException{
        open(url, "", username, password);
        Thread.sleep(3000);
        String text = $(byXpath("//p")).getText();
        Assert.assertTrue(text.contains(expected_value));
    }
}
