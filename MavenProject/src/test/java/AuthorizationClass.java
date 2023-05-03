import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationClass extends BaseClass{
    @Test/* Description
        ID. A_1
        Заголовок. Авторизация.
        Предусловие. Браузер – Chrome.
        Шаги.
        1.	Открыть “https://the-internet.herokuapp.com/basic_auth”
        2.	Авторизоваться (username: “admin”, password: “admin”)
        Ожидаемый результат. Откроется страница, содержащая текст “Congratulations! You must have the proper credentials.”.
    */
    public void authorizationTest() throws InterruptedException{
        openUrl("https://www.google.ru/search?q=pig");
        ElementsCollection elements = $$(byCssSelector(".LC20lb")).shouldHave(CollectionCondition.size(17));
        Assert.assertEquals(elements.size(), 17);
    }
}
