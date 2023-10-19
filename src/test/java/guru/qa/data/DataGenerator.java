package guru.qa.data;

import com.github.javafaker.Faker;

public class DataGenerator {
    public String getBookId() {
        String[] bookId = {"9781449325862", "9781449331818", "9781449337711", "9781449365035", "9781491904244", "9781491950296", "9781593275846", "9781593277574"};
        return new Faker().options().option(bookId);
    }
}
