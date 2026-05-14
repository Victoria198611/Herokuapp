package com.victoria.herokuapp.data;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider
    public static Object[][] invalidLoginData() {
        return new Object[][]{
                {"wrongUser", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "wrongPass", "Your password is invalid!"},
                {"", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "", "Your password is invalid!"}
        };
    }
}