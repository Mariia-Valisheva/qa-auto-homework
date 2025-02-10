package tests.alluretests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class AllureTestBase {
    @BeforeAll
    static void generalConfigParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1440x932";
        //Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    void configParams() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void configParamsForEach() {
        Selenide.closeWebDriver();
    }
}
