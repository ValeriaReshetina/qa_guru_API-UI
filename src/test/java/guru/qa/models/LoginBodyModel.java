package guru.qa.models;

import lombok.Data;

@Data
public class LoginBodyModel {
    private String password, userName;
}
