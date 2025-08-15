package herokuapp;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.Selenide.open;

public class DragAndDropTest {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://the-internet.herokuapp.com/";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    public void dragAndDropSquareUsingActionsTest() {
        open("/drag_and_drop");

        SelenideElement leftSquare = $("#column-a").shouldHave(text("A"));
        SelenideElement rightSquare = $("#column-b").shouldHave(text("B"));

        actions().clickAndHold(leftSquare).moveToElement(rightSquare).release().perform();

        // Проверка, что прямоугольники поменялись местами
        leftSquare.shouldHave(text("B"));
        rightSquare.shouldHave(text("A"));
    }

    @Test
    public void dragAndDropSquareUsingSelenideMethodTest() {
        open("/drag_and_drop");

        SelenideElement leftSquare = $("#column-a").shouldHave(text("A"));
        SelenideElement rightSquare = $("#column-b").shouldHave(text("B"));

        leftSquare.dragAndDrop(to(rightSquare));

        // Проверка, что прямоугольники поменялись местами
        leftSquare.shouldHave(text("B"));
        rightSquare.shouldHave(text("A"));
    }
}
