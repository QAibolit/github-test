package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideRepositoryTest {

    @BeforeAll
    public static void setUp(){
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void containsJUnitCodeOnSoftAssertionsPage() {
        // Открыть страницу Selenide в Github
        open("");
        $(".header-search-button").click();
        $("#query-builder-test").shouldBe(visible).setValue("selenide").pressEnter();
        $("[data-testid='results-list']").shouldBe(visible);
        $("a[href$='/selenide']").click();

        // Перейти в раздел Wiki проекта
        $("a#wiki-tab").shouldBe(visible).click();

        // Убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        $("a[href$='SoftAssertions']").shouldBe(visible).click();

        // Открыть страницу SoftAssertions, проверить что внутри есть пример кода для JUnit5
        $("[class$=header-title]").shouldHave(text("SoftAssertions"));
        $(withTagAndText("h4", "JUnit5")).shouldHave(text("Using JUnit5 extend test class:"));
        $(withTagAndText("h4", "JUnit5")).parent().sibling(0).$("pre").shouldBe(visible);
    }
}
