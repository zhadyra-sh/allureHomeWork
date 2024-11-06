package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {
    private static final String REPOSITORY = "zhadyra-sh/allureHomeWork";
    @DisplayName("Тест на поиск Issue в репозитории с помощью lambda")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Test page", url = "https://github.com/zhadyra-sh/allureHomeWork")

    @Test
    public void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () ->
                open("https://github.com"));
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () ->
                $(linkText(REPOSITORY)).click());
        step("Проверяем наличие Issue",  () -> {
            $("#issues-tab").shouldHave(text("Issues"));
        });
    }

    @Test
    @DisplayName("Тест на поиск Issue в репозитории с помощью аннотацией Step")
    @Severity(SeverityLevel.TRIVIAL)
    @Link(value = "Test page", url = "https://github.com/zhadyra-sh/allureHomeWork")

    public void annotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebTest steps = new WebTest();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.shouldSeeIssue();
    }


}
