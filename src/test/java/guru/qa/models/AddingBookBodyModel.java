package guru.qa.models;

import lombok.Data;

import java.util.List;

@Data
public class AddingBookBodyModel {

    private String userId;
    private List<IsbnModel> collectionOfIsbns;

    public void addIsbnToList(IsbnModel isbnModel) {
        collectionOfIsbns.add(isbnModel);
    }
}
