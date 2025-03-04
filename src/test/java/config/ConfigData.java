package config;

import com.codeborne.selenide.Browser;
import org.aeonbits.owner.Config;

import java.net.URL;

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
    @DefaultValue("CHROME")
    String browser();

    @Key("remoteUrl")
    @DefaultValue("http://localhost:4444")
    URL remoteUrl();

    @Key("browserSize")
    @DefaultValue("1440x932")
    String browserSize();

    @Key("browserVersion")
    @DefaultValue("125")
    String browserVersion();

    @Key("isRemote")
    boolean isRemote();
}
