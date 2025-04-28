package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;


public class SelenideTest {

    @BeforeAll
    public static void setUp() {

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getProperty("remoteURL");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

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

    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }

}

