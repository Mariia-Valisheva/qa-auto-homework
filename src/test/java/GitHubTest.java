import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GitHubTest {

    @BeforeAll
    static void configParams() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideGitHubTest() {

        open("/selenide/selenide");
        actions().moveToElement($("#wiki-tab")).click().perform();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(".filterable-active").shouldHave(text("SoftAssertions"));
        $(".filterable-active").$(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class}) class Tests { @Test void test() { Configuration.assertionMode = SOFT; open(\"page.html\"); $(\"#first\").should(visible).click(); $(\"#second\").should(visible).click(); } }"));

    }
}



