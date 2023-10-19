package guru.qa.api;

import guru.qa.models.AddingBookBodyModel;
import guru.qa.models.IsbnModel;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import static guru.qa.specs.StoreSpec.*;
import static io.restassured.RestAssured.given;

public class BookApi {
    @Step("Add a book")
    public static void bookAdding(String bookId, String userId, String token) {

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(bookId);
        List<IsbnModel> isbn = new ArrayList<>();
        isbn.add(isbnModel);

        AddingBookBodyModel bookAddingInfo = new AddingBookBodyModel();
        bookAddingInfo.setCollectionOfIsbns(isbn);
        bookAddingInfo.setUserId(userId);

        given(bookAddingSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookAddingInfo)
                .when()
                .post()
                .then()
                .spec(bookAddingResponseSpec);
    }

    @Step("Delete all books from a user's profile")
    public static void bookDeletion(String userId, String token) {

        given(bookDeletionRequestSpec)
                .header("Authorization", "Bearer " + token)
                .param("UserId", userId)
                .when()
                .delete()
                .then()
                .spec(bookDeletionResponseSpec);
    }
}
