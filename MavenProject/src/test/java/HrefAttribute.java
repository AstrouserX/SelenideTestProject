import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class HrefAttribute extends BaseClass{
    private final static String url = "https://ru.wikipedia.org/";
    @Test/* Description
        ID. HA_1
        Заголовок. Href-атрибут.
        Предусловие. Браузер – Chrome.
        Шаги.
        1.	Открыть сайт Wikipedia
        2.	Получить коллекцию элементов из последних 10 <a>- тегов, у которых есть текст
        3.	Проверить наличие атрибута “href” в первом элементе коллекции
        Ожидаемый результат. У первого элемента коллекции должен быть не пустой href-атрибут.
    */
    public void hrefAttributeTest(){
        openUrl(url);
        ElementsCollection site_links = $$(By.tagName("a"));
        site_links.last(10).stream().filter(e -> !e.getText().isEmpty());
        Assert.assertTrue(!site_links.last(1).get(0).getAttribute("href").isEmpty());
    }
}
