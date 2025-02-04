package tests.filetests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class FileTestBase {


    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1440x932";
        //Configuration.holdBrowserOpen = true;
    }


    @AfterEach
    void configParamsForEach() {
        Selenide.closeWebDriver();
    }
}
