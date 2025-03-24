package tests.githubtests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тест на поиск в гитхабе")
@Tag("WEB")

public class GitHubTest extends TestBaseGit {

    @Test
    @DisplayName("Поиск информации в разделе SoftAssertions Wiki")
    @Tag("github")
    void selenideGitHubTest() {

        open("/selenide/selenide");
        actions().moveToElement($("#wiki-tab")).click().perform();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(".filterable-active").shouldHave(text("SoftAssertions"));
        $(".filterable-active").$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text(
                "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "   @Test\n" +
                "    void test() {\n" +
                "       Configuration.assertionMode = SOFT;\n" +
                "       open(\"page.html\");\n" +
                "\n" +
                "       $(\"#first\").should(visible).click();\n" +
                "       $(\"#second\").should(visible).click();\n" +
                "       }\n" +
                "}"));

    }
}

