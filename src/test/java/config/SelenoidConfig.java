package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:credentials.properties",
        "system:env"
})

public interface SelenoidConfig extends Config {

    @Key("SELENOID_LOGIN")
    String username();

    @Key("SELENOID_PASS")
    String password();
}
