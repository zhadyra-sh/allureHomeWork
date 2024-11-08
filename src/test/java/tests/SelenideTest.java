package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;


public class SelenideTest {

    @BeforeAll
    public static void setUp() {

        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("Allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    @Tag("regress")
    @DisplayName("Тест на поиск Issue в репозитории")
    @Owner("Zhadyra Shynybayeva")
    @Test
    public void issueNameSearchTest() {

        open("https://github.com");

        $(".search-input").click();
        $("#query-builder-test").sendKeys("zhadyra-sh/allureHomeWork");
        $("#query-builder-test").submit();

        $(linkText("zhadyra-sh/allureHomeWork")).click();
        $("#issues-tab").shouldHave(text("Issues"));
    }
}
