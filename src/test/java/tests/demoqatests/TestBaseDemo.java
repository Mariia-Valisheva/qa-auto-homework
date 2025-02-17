package tests.demoqatests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationFormPage;

import java.util.Map;

public class TestBaseDemo {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        //Configuration.holdBrowserOpen = true;

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeAll
    static void addSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Step("Открываем страницу регистрации")
    @BeforeEach
    void openPageBeforeEachTest() {
        registrationFormPage.openPage();
    }


    @AfterEach
    void configParamsForEach() {
        Selenide.closeWebDriver();
    }
}
