package org.example;

import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class AppTest{
    @BeforeTest
    public void preparation(){
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920*1080";
    }
    @Test
    public void GoogleSearch() throws InterruptedException {
        open("https://www.google.ru");
        Assert.assertTrue(WebDriverRunner.isChrome());
        Assert.assertTrue(WebDriverRunner.supportsJavascript());
        $(byName("q")).setValue("pigs").pressEnter();
        refresh();
        SelenideElement logo = $(byId("logo")).shouldHave(Condition.appear);
        Assert.assertTrue(logo.exists());
        ElementsCollection elements = $$(byCssSelector(".LC20lb")).shouldHave(CollectionCondition.size(17));
        Assert.assertEquals(elements.size(), 17);
        elements.get(1).click();
        System.out.println(WebDriverRunner.url());
        Thread.sleep(3000);
        switchTo().window(0);
        Thread.sleep(3000);
        switchTo().window(1);
        Thread.sleep(3000);
        closeWindow();
        Thread.sleep(3000);
        closeWebDriver();
    }
    @Test
    public void basicAuth() throws InterruptedException {
        open("https://the-internet.herokuapp.com/basic_auth", "", "admin", "admin");
        Thread.sleep(3000);
        String text = $(byXpath("//p")).getText();
        Assert.assertTrue(text.contains("credentials"));
    }
    @Test
    public void linksList(){
        open("https://ru.wikipedia.org/");
        ElementsCollection site_links = $$(By.tagName("a"));
        site_links.stream().filter(e -> !e.getText().isEmpty());
        System.out.println("------------------------");
        site_links.last(10).stream().filter(e -> !e.getText().isEmpty()).forEach(e -> System.out.println(e.getText()));
        Assert.assertTrue(!site_links.last(1).get(0).getAttribute("href").isEmpty());
    }
    @Test
    public void selectOption() throws InterruptedException {
        open("https://opensource-demo.orangehrmlive.com");
        $(By.name("username")).val("Admin");
        $(By.name("password")).val("admin123").pressEnter();
        $(byText("PIM")).click();
        ElementsCollection buttons = $$(By.cssSelector(".bi-trash"));
        buttons.get(1).click();
        Thread.sleep(3000);
        //ElementsCollection yesButtons = ($$(By.tagName("button"))).shouldBe(CollectionCondition.);
        //System.out.println(yesButtons.stream().filter(e -> e.text().contains("Yes")).count());
        SelenideElement yesButton = $(byXpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]"));
        System.out.println(yesButton.getTagName());
        yesButton.click();
        Thread.sleep(3000);
    }
    @Test
    public void urlHrefCheck(){
        Random random = new Random();
        open("https://www.google.com/");
        Assert.assertTrue(WebDriverRunner.isChrome());
        $(byName("q")).setValue("cats").pressEnter();
        ElementsCollection links = $$(byTagName("a"));
        links.stream().filter(e -> !e.getAttribute("href").isEmpty());
        int randomNum = ThreadLocalRandom.current().nextInt(0, links.size() - 1);
        Assert.assertTrue(!links.get(randomNum).getAttribute("href").isEmpty());
    }
    @AfterTest
    public void theEnd(){
        Selenide.closeWebDriver();
    }
}
