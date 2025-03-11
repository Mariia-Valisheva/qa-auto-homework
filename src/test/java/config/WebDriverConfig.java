package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverConfig {

    private final ConfigData configData = ConfigFactory.create(ConfigData.class, System.getProperties());
    private final SelenoidConfig selenoidConfig = ConfigFactory.create(SelenoidConfig.class, System.getProperties());

    public void configParams() {
        Boolean isRemote = Boolean.valueOf(System.getProperty("isRemote", "false"));
        String environment = System.getProperty("env");

        Configuration.pageLoadStrategy = "eager";
        if (isRemote) {
            if (environment == "remote") {
                Configuration.baseUrl = configData.baseUrl().toString();
                Configuration.browser = configData.browser();
                Configuration.browserSize = configData.browserSize();
                Configuration.browserVersion = configData.browserVersion();

                String SELENOID_HOST = configData.remoteUrl().toString();
                String username = selenoidConfig.username();
                String password = selenoidConfig.password();
                Configuration.remote = "https://" + username + ":" + password + SELENOID_HOST + "/wd/hub";

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                        "enableVNC", true,
                        "enableVideo", true
                ));
                Configuration.browserCapabilities = capabilities;
            }

            Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
            Configuration.browserSize = System.getProperty("browserSize", "1440x932");
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.browserVersion = System.getProperty("browserVersion");

            String SELENOID_HOST = System.getProperty("selenoidHost");
            String username = selenoidConfig.username();
            String password = selenoidConfig.password();
            Configuration.remote = "https://" + username + ":" + password + SELENOID_HOST + "/wd/hub";

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;

        } else {
            Configuration.baseUrl = configData.baseUrl().toString();
            Configuration.browser = configData.browser();
            Configuration.browserSize = configData.browserSize();
            Configuration.browserVersion = configData.browserVersion();
        }
    }
}
