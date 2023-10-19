package guru.qa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private SelenideElement noDataGridElement = $x("//div[@class='rt-noData']");

    @Step("Check that there are no books in the table")
    public void emptyTableCheck() {
        noDataGridElement.shouldHave(Condition.text("No rows found"));
    }
}
