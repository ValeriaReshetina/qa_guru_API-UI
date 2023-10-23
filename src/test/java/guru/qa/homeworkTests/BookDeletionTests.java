package guru.qa.homeworkTests;

import guru.qa.data.TestData;
import guru.qa.models.LoginResponseModel;
import guru.qa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.api.AuthorizationApi.successfulAuthorisation;
import static guru.qa.api.BookApi.bookAdding;
import static guru.qa.api.BookApi.bookDeletion;

public class BookDeletionTests extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    TestData testData = new TestData();

    @DisplayName("Deleting book from user profile")
    @Test
    void bookDeletionTest(){
        LoginResponseModel loginResponse = successfulAuthorisation(testData.login, testData.password);
        bookDeletion(loginResponse.getUserId(), loginResponse.getToken());
        bookAdding(testData.bookId,loginResponse.getUserId(), loginResponse.getToken());
        bookDeletion(loginResponse.getUserId(), loginResponse.getToken());

        open(baseUrl + "/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));

        open(baseUrl + "/profile");
        profilePage.emptyTableCheck();
    }
}
