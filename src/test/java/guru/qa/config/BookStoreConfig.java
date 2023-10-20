package guru.qa.config;
import org.aeonbits.owner.Config;

@BookStoreConfig.Sources({
        "classpath:config.properties"
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
}

