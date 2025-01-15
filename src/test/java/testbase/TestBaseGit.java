package testbase;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBaseGit {

    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "2560x1440";
        //Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void configParamsForEach() {
        Selenide.closeWebDriver();
    }
}
