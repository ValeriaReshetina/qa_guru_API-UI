package guru.qa.config;
import org.aeonbits.owner.Config;

@BookStoreConfig.Sources({
        "classpath:local.properties"
})

public interface BookStoreConfig extends Config {

    @Key("baseUrl")
    String baseUrl();

    @Key("browser")
    String browser();

    @Key("browserVersion")
    String browserVersion();

    @Key("browserSize")
    String browserSize();

    @Key("remoteUrl")
    @DefaultValue("")
    String getRemoteUrl();

    @Key("remoteAuth")
    @DefaultValue("")
    String getRemoteAuth();
}

