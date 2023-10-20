package guru.qa.homeworkTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.config.BookStoreConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static guru.qa.helpers.AttachHelper.*;

public class TestBase {
    protected static final BookStoreConfig config = ConfigFactory.create(BookStoreConfig.class, System.getProperties());

    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserSize();

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

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        screenshotAs("Last screenshot");
        pageSource();
        browserConsoleLogs();
        addVideo();
        closeWebDriver();
    }
}
