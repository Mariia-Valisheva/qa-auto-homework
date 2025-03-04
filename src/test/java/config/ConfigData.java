package config;

import org.aeonbits.owner.Config;

import java.net.URL;

import static com.codeborne.selenide.Browsers.CHROME;

@Config.Sources(
        {
                "classpath:local.properties"
        }
)

public interface ConfigData extends Config {

    @Key("baseUrl")
    @DefaultValue("https://demoqa.com")
    URL baseUrl();

    @Key("browser")
    @DefaultValue(CHROME)
    String browser();

    @Key("browserSize")
    @DefaultValue("1440x932")
    String browserSize();

    @Key("browserVersion")
    @DefaultValue("125")
    String browserVersion();

    @Key("isRemote")
    @DefaultValue("false")
    boolean isRemote();
}
