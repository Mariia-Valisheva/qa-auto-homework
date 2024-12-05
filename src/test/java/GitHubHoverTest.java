import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GitHubHoverTest {

    @BeforeAll
        static void configParams() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "2560x1440";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }
    @Test
    void hoverTest() {

    }
}
