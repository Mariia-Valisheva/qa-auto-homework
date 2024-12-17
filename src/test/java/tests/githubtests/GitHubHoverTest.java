package tests.githubtests;

import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GitHubHoverTest extends TestBaseGit {

    @Test
    void hoverTest() {
        open("");
        $(".HeaderMenu-nav").$(byText("Solutions")).hover();
        $(byTagAndText("a","Enterprises")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));
    }

    //drag and drop with actions

    @Test
    void dragAndDropTestViaActions() {

       open("https://the-internet.herokuapp.com/drag_and_drop");
       actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
       $("#column-a").shouldHave(text("B"));
    }

    //drag and drop with selenide command

    @Test
    void dragAndDropTestViaCommand() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDrop(DragAndDropOptions.to($("#column-b")));
        $("#column-a").shouldHave(text("B"));
    }
}




