package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.allOf;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubEnterpriseTest {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void openEnterprisePlatformPageTest() {
        open("");
        $("#hero-section-brand-heading").shouldHave(text("Build and ship software on a single, collaborative platform"));
        $(withTagAndText("button", "Solutions")).shouldBe(visible).hover();
        $(withTagAndText("button", "Solutions")).sibling(0).shouldBe(visible);
        $("a[href$='/enterprise']").shouldBe(visible).click();

        // Проверка загрузки страницы Enterprise
        $("#hero-section-brand-heading").shouldBe(visible).shouldHave(text("The AI-powered developer platform"));
        $(".enterprise-hero-subtitle").shouldBe(visible).shouldHave(text("To build, scale, and deliver secure software"));
        $("a[href$='/enterprises/new']").shouldBe(allOf(visible, enabled));
        $("a[href*='Contact+Sales']").shouldBe(allOf(visible, enabled));
    }
}
