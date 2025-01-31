package tests.filetests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class FileTestBase {


    @BeforeAll
    static void configParams() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "2560x1440";
        Configuration.holdBrowserOpen = true;
    }


//    @AfterEach
//    void configParamsForEach() {
//        Selenide.closeWebDriver();
//    }
}
