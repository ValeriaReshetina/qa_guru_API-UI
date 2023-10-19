package guru.qa.classworkTests;

import com.codeborne.selenide.Configuration;
import guru.qa.homeworkTests.TestBase;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class LoginTests {
    String login = "qa@qa.guru", password = "qa@qa.guru1";
    @Test
    void loginWithUITest() {
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
        step("Open login page", () ->
                open("/login"));
        step("Fill login form", () -> {
            $("#Email").setValue(login);
            $("#Password").setValue(password)
                    .pressEnter();
        });
        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(login)));
    }

    @Test
    void loginWithApiTest() {
        Configuration.baseUrl = "https://demowebshop.tricentis.com";
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
        step("Get authorization cookie by api and set it to browser", () -> {
            String authCookieKey = "NOPCOMMERCE.AUTH";
            String authCookieValue = given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", login)
                    .formParam("Password", password)
                    .when()
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract()
                    .cookie(authCookieKey);

            open("/Content/jquery-ui-themes/smoothness/images/ui-bg_flat_75_ffffff_40x100.png");
            Cookie authCookie = new Cookie(authCookieKey, authCookieValue);
            getWebDriver().manage().addCookie(authCookie);
        });

        step("Open main page", () ->
                open(""));

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(login)));
    }
}
