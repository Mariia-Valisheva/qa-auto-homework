package tests.githubtests;

import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты с действиями мышки")
@Tag("WEB")
public class GitHubHoverTest extends TestBaseGit {

    @Test
    @DisplayName("Наведение мышкой на меню в шапке страницы")
    @Tags(
            {
                    @Tag("GITHUB"),
                    @Tag("Hover")
            }
    )

    void hoverTest() {
        open("");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover();
        $(byTagAndText("a","Enterprises")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));
    }


    @Disabled("TASK-123")
    @Test
    @DisplayName("Перетаскивание квадрата с одного места на другое через actions")
    @Tag("DRAGANDDROP")

    void dragAndDropTestViaActions() {

       open("https://the-internet.herokuapp.com/drag_and_drop");
       actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
       $("#column-a").shouldHave(text("B"));
    }

    @Test
    @DisplayName("Перетаскивание квадрата с одного места на другое через команду selenide")
    @Tag("DRAGANDDROP")

    void dragAndDropTestViaCommand() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDrop(DragAndDropOptions.to($("#column-b")));
        $("#column-a").shouldHave(text("B"));
    }
}




