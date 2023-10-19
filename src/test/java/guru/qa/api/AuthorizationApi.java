package guru.qa.api;

import guru.qa.models.LoginBodyModel;
import guru.qa.models.LoginResponseModel;
import io.qameta.allure.Step;

import static guru.qa.specs.StoreSpec.loginRequestSpec;
import static guru.qa.specs.StoreSpec.loginResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {
    @Step("Authorization")
    public static LoginResponseModel successfulAuthorisation(String login, String password) {

        LoginBodyModel userCredentials = new LoginBodyModel();
        userCredentials.setUserName(login);
        userCredentials.setPassword(password);

        return
                given(loginRequestSpec)
                        .body(userCredentials)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class);
    }
}
