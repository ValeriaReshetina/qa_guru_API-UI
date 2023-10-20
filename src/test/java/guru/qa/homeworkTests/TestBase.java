package guru.qa.homeworkTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.config.BookStoreConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {
    protected static final BookStoreConfig config = ConfigFactory.create(BookStoreConfig.class, System.getProperties());

    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserSize();
        Configuration.pageLoadStrategy = "eager";

        String selenoidHome = config.getRemoteUrl();
        String selenoidCredentials = config.getRemoteAuth();
        if (!(selenoidHome.isEmpty() || selenoidCredentials.isEmpty())) {
            Configuration.remote = "https://" + selenoidCredentials + "@" + selenoidHome + "/wd/hub";

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));

        }
    }

    @AfterAll
    static void afterAll() {
        Selenide.closeWebDriver();
    }
}
