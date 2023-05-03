import com.codeborne.selenide.ElementsCollection;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSitesHref {
    @Test/* Description
        ID. GLWT_1
        Заголовок. Href-ссылки.
        Предусловие. Браузер – Chrome.
        Шаги.
        1.	Открыть главную страницу google
        2.	Ввести в поисковую строку значение, содержащее символы латиницы или кириллицы
        3.	Получить коллекцию элементов из тегов <a>
        4.	Проверить есть ли текст у атрибута href у случайно взятого элемента из коллекции
        Ожидаемый результат. В полученном элементе href-атрибут не должен быть пустым
    */
    public void GLWT_1(){
        Random random = new Random();
        open("https://www.google.com/");
        $(byName("q")).setValue("cats").pressEnter();
        ElementsCollection links = $$(byTagName("a"));
        int randomNum = ThreadLocalRandom.current().nextInt(0, links.size() - 1);
        Assert.assertTrue(!links.get(randomNum).getAttribute("href").isEmpty());
    }
}
