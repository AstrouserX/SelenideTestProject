import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.Advice.OnMethodEnter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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

6. (Проверка) Нажать на кнопку, содержащую текст "Почта"
6. (Ожидаемый результат) Открылась страница с адресом "https://www.google.com/intl/ru/gmail/about/'

7. (Проверка) Нажать на ссылку, содержащую текст "Всё о Google' в левой нижней части страницы
7. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Цель компании"

8. (Проверка) Нажать на ссылку, содержащую текст "Реклама" в левой нижней части страницы
8. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Развивайте свою компанию с Google Рекламой"

9. (Проверка) Нажать на ссылку, содержащую текст "Для бизнеса" в левой нижней части страницы
9. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Ваша компания в сервисах Google"

10. (Проверка) Нажать на ссылку, содержащую текст "Как работает Google Поиск" в левой нижней части страницы
10. (Ожидаемый результат) Открылась страница с заголовком, содержащим текст "Наша цель – систематизировать всю имеющуюся в мире информацию и сделать ее доступной для каждого пользователя."

11. (Проверка) Нажать на ссылку, содержащую текст "Конфиденциальность" в правой нижней части страницы
11. (Ожидаемый результат) Открылась страница содержащая текст "Политика конфиденциальности" в левой верхней части страницы рядом с логотипом

     */
    public void linkTextClick(String tag, String txt){
        $(byTagAndText(tag, txt)).click();
    }

    @BeforeMethod
    public void openUrl(){
        open("https://www.google.ru");
    }

    @Test(description = "Test1 Гость может перейти в календарь событий нажав на кнопку 'Мне повезет'")
    public void testLuckyButton(){
        $(byXpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[2]")).click();
        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(), "https://www.google.com/doodles");
    }

    @Test(description = "Test2 Гость может ознакомиться с условиями использования перейдя по ссылке 'Условия'")
    public void testConditionsUrl(){
        linkTextClick("a","Условия");
        Assert.assertTrue($(byId("sdgBod")).text().contains("Политика конфиденциальности"));
    }

    @Test(description = "Test3 Как работает google-поиск")
    public void testSearch(){
        $(byName("q")).val("pigs").pressEnter();
        ElementsCollection elements = $$(byCssSelector(".LC20lb"));
        Assert.assertEquals(elements.size(), 17);
    }

    @Test(description = "Test4 Гость может перейти к настройкам браузера нажав на ссылку 'Настройки'")
    public void testSettingsUrl(){
        $(byTagAndText("div", "Настройки")).click();
        Assert.assertTrue($$(byTagName("g-menu-item")).get(0).text().contains("Настройки поиска"));
    }

    @Test(description = "Test5 Гость может перейти к поиску изображений перейдя по ссылке 'Картинки'")
    public void testImagesUrl(){
        linkTextClick("a","Картинки");
        Assert.assertTrue($(byTagAndText("span","Картинки")).exists());
    }

    @Test(description = "Test6 Пользователь может перейти в почтовый ящик перейдя по ссылке 'Почта'")
    public void testGmailUrl(){
        linkTextClick("a", "Почта");
        Assert.assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().contains("https://www.google.com/intl/ru/gmail/about/") || WebDriverRunner.getWebDriver().getCurrentUrl().contains("https://accounts.google.com/v3/signin"));
    }

    @Test(description = "Test7 Гость может ознакомиться с информацией о компании перейдя по ссылке 'Всё о Google'")
    public void testAboutUrl(){
        linkTextClick("a","Всё о Google");
        Assert.assertTrue($(byCssSelector(".glue-grid__col--align-middle > h1")).text().contains("Цель компании"));
    }

    @Test(description = "Test8 Гость может ознакомиться с рекламной компанией перейдя по ссылке 'Реклама'")
    public void testAdsUrl(){
        linkTextClick("a","Реклама");
        Assert.assertTrue($(byTagAndText("span","Развивайте свою компанию с Google Рекламой")).exists());
    }

    @Test(description = "Test9 гость может ознакомиться с условиями для бизнеса перейдя по ссылке 'Для бизнеса'")
    public void testBusinessUrl(){
        linkTextClick("a","Для бизнеса");
        Assert.assertEquals($(byCssSelector(".heading")).text(), "Ваша компания в сервисах Google");
    }

    @Test(description = "Test10 Гость может ознакомиться с с тем, как работает поисковая система перейдя по ссылке 'Как работает Google Поиск'")
    public void testSearchWorkUrl(){
        linkTextClick("a", "Как работает Google Поиск");
        Assert.assertEquals($(byCssSelector(".homepage-hero__content__title")).shouldBe(Condition.visible).text(), "Наша цель – систематизировать всю имеющуюся в мире информацию и сделать ее доступной для каждого пользователя.");
    }

    @Test(description = "Test11 Гость может ознакомиться с политикой конфиденциальности перейдя по ссылке 'Конфиденциальность'")
    public void testConfidentialUrl(){
        linkTextClick("a","Конфиденциальность");
        Assert.assertTrue($(byTagAndText("span","Политика конфиденциальности")).exists());
    }
}
