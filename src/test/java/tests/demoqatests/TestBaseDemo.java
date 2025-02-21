package tests.demoqatests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attachments;
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
        Configuration.browserSize = System.getProperty("browser_size", "1440x932");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version");

        String SELENOID_HOST = System.getProperty("selenoid_host");
        Configuration.remote = "https://user1:1234@" + SELENOID_HOST + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        //Configuration.holdBrowserOpen = true;
    }


    @Step("Открываем страницу регистрации")
    @BeforeEach
    void addSelenideListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    void openPageBeforeEachTest() {
        registrationFormPage.openPage();
    }


    @AfterEach
    void addAttachments() {

        if (!Configuration.browser.equals("firefox")) {
            Attachments.addScreenshot("Test screenshot");
            Attachments.addPageSource();
            Attachments.addBrowserConsoleLogs();
            Attachments.addVideo();
        }

        Selenide.closeWebDriver();
    }
}
