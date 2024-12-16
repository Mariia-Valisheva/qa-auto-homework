package GitHubTests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GitHubTest extends TestBase {

    @Test
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



