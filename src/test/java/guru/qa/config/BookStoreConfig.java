package guru.qa.config;
import org.aeonbits.owner.Config;

@org.aeonbits.owner.Config.Sources({
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
}

