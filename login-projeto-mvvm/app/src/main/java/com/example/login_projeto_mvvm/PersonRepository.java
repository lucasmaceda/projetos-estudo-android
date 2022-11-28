package com.example.login_projeto_mvvm;

public class PersonRepository {

    public boolean login(String email, String password) {
        return (email.trim().equals("") && password.trim().equals(""));
    }

}
