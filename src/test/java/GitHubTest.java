import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class GitHubTest {

    @BeforeAll
    static void configParams() {
        Configuration.baseUrl = "https://github.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideGitHubTest() {

        // Откройте страницу Selenide в Github
        open("/selenide/selenide");

        // Перейдите в раздел Wiki проекта
        actions().moveToElement($("#wiki-tab")).click().perform();




    }
}

// Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions

// Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5