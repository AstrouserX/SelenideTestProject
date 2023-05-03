import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class TestGoogleClass extends BaseClass{
    /*

1. (Проверка) Нажать на кнопку, содержащую текст "Мне повезет!"
1. (Ожидаемый результат) Открылась страница с адресом "https://www.google.com/doodles"

2. (Проверка) Нажать на ссылку "Условия" в правой нижней части страницы
2. (Ожидаемый результат) Открылась страница содержащая текст "Политика конфиденциальности" в левой верхней части страницы рядом с логотипом

3. (Проверка) Ввести запрос, состоящий из кириллицы или латиницы, в поисковую строку
3. (Ожидаемый результат) Открылась страница с результатами поиска, содержащая 17 ссылок на страницы

4. (Проверка) Нажать на ссылку, содержащую текст "Настройки" в правой нижней части страницы
4. (Ожидаемый результат) Открылся выпадающий список с пунктом, содержащим текст "Настройки поиска"

5. (Проверка) Нажать на ссылку, содержащую текст "Картинки"
5. (Ожидаемый результат) Под логотипом отобразился текст "Картинки"

6. (Проверка) Нажать на ссылку, содержащую текст "Почта"
6. (Ожидаемый результат) Открылась страница с адресом "https://www.google.com/intl/ru/gmail/about/'

7. (Проверка) Нажать на ссылку, содержащую текст "Всё о Google' в левой нижней части страницы
7. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Цель компании"

8. (Проверка) Нажать на ссылку, содержащую текст "Реклама" в левой нижней части страницы
8. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Развивайте свою компанию с Google Рекламой"

9. (Проверка) Нажать на ссылку, содержащую текст "Для бизнеса" в левой нижней части страницы
9. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Ваша компания в сервисах Google"

10. (Проверка) Нажать на ссылку, содержащую текст "Как работает Google Поиск" в левой нижней части страницы
10. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Наша цель – систематизировать всю имеющуюся в мире информацию и сделать ее доступной для каждого пользователя"

11. (Проверка) Нажать на ссылку, содержащую текст "Конфиденциальность" в правой нижней части страницы
11. (Ожидаемый результат) Открылась страница содержащая текст "Политика конфиденциальности" в левой верхней части страницы рядом с логотипом

     */
    private final static String url = "https://www.google.ru";

    public void linkTextClick(String tag, String txt){
        $(byTagAndText(tag, txt)).click();
    }

    @Test(description = "Test1 Как работает кнопка 'Мне повезет'")
    public void testLuckyButton(){
        openUrl(url);
        $(byXpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]")).click();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://www.google.com/doodles");
    }
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void testConditionsUrl(){
        openUrl(url);
        linkTextClick("a","Условия");
        Assert.assertTrue($(byId("sdgBod")).text().contains("Политика конфиденциальности"));
    }
    @Test(description = "Test3 Как работает google-поиск")
    public void testSearch(){
        openUrl(url);
        $(byName("q")).val("pigs").pressEnter();
        ElementsCollection elements = $$(byCssSelector(".LC20lb"));
        Assert.assertEquals(elements.size(), 17);
    }
    @Test(description = "Test4 Как работает ссылка 'Настройки'")
    public void testSettingsUrl(){
        openUrl(url);
        //linkTextClick("Настройки");
        $(byTagAndText("div", "Настройки")).click();
        Assert.assertTrue($$(byTagName("g-menu-item")).get(0).text().contains("Настройки поиска"));
    }/*
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void test(){
        openUrl(url);
        linkTextClick("");
    }
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void test(){
        openUrl(url);
        linkTextClick("");
    }
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void test(){
        openUrl(url);
        linkTextClick("");
    }
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void test(){
        openUrl(url);
        linkTextClick("");
    }
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void test(){
        openUrl(url);
        linkTextClick("");
    }
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void test(){
        openUrl(url);
        linkTextClick("");
    }
    @Test(description = "Test2 Как работает ссылка 'Условия'")
    public void test(){
        openUrl(url);
        linkTextClick("");
    }*/
}
