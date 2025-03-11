package config;

import org.aeonbits.owner.Config;

public interface SelenoidConfig extends Config {

    @Key("SELENOID_LOGIN")
    String username();

    @Key("SELENOID_PASS")
    String password();
}
