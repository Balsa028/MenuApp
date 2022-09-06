package com.balsa.menuapp.Login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginFragmentTest {

    @Test
    void enteringValidCredentialsScenario(){
        LoginFragment fragment = new LoginFragment();
        boolean actual = fragment.validateCredentials("test@testmenu.app", "test1234");

        assertTrue(actual);
    }
}