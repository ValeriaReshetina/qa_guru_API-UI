package guru.qa.homeworkTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.config.BookStoreConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    protected static final BookStoreConfig config = ConfigFactory.create(BookStoreConfig.class, System.getProperties());

    @BeforeAll
    public static void beforeAll(){
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser();
        Configuration.browserVersion = config.browserVersion();
        Configuration.browserSize = config.browserSize();
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterAll
    static void afterAll() {
        Selenide.closeWebDriver();
    }
}
