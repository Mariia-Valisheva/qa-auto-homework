package config;

import org.aeonbits.owner.Config;

@Config.Sources(
        {
                "file:/Users/pirozhok/IdeaProjects/homework1/src/test/resources/credentials.properties"
        }
)

public interface CredentialsConfig extends Config {

    @Key("username")
    String username();

    @Key("password")
    String password();
}
