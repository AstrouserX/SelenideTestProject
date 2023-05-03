import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleSearchTest extends BaseClass{
    @Test/* Description
    ID. GSP_1
        Заголовок. Ссылки на страницы.
        Предусловие. Браузер – Chrome.
        Шаги.
        1.	Открыть главную страницу google
        2.	Ввести в поисковую строку значение, содержащее символы латиницы или кириллицы
        3.	Нажать кнопку enter
        4.	Получить количество ссылок, ведущих на сайты
        Ожидаемый результат. Количество ссылок – 17.
    */
    public void searchTest(){
        openUrl("https://www.google.ru/search?q=pig");
        ElementsCollection elements = $$(byCssSelector(".LC20lb")).shouldHave(CollectionCondition.size(17));
        Assert.assertEquals(elements.size(), 17);
    }
}
