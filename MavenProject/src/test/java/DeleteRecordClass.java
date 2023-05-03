import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DeleteRecordClass {
    @Test/* Description
        ID. CB_1
        Заголовок. Удаление.
        Предусловие. Браузер – Chrome. Нужно быть авторизованным (username: “Admin”, password: “admin123”).
        Шаги.
        1.	Открыть сайт "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList"
        2.	Нажать на кнопку, содержащую иконку “мусорки”
        3.	Нажать на кнопку, содержащую текст “Yes, Delete”
        Ожидаемый результат. В левой нижней части страницы отобразилось всплывающее сообщение, содержащее заголовок “Success”.
    */
    public void deleteRecordTest() throws InterruptedException {
        open("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
        $(By.name("username")).val("Admin");
        $(By.name("password")).val("admin123").pressEnter();
        ElementsCollection buttons = $$(By.cssSelector(".bi-trash"));
        buttons.get(1).click();
        Thread.sleep(3000);
        SelenideElement yesButton = $(byXpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]"));
        System.out.println(yesButton.getTagName());
        yesButton.click();
        Thread.sleep(3000);
        SelenideElement succes_notice = $(byCssSelector(".oxd-text--toast-title")).shouldBe(Condition.visible);
        Assert.assertEquals(succes_notice.text(), "Success");
    }
}
