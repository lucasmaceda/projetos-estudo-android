package com.example.login_projeto_mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> textWelcome = new MutableLiveData<>();
    private MutableLiveData<Boolean> login = new MutableLiveData<>();
    private PersonRepository repository = new PersonRepository();

    public MainViewModel() {
        textWelcome.setValue("Olá");
    }

    public LiveData<String> welcome() {
        return textWelcome;
    }

    public LiveData<Boolean> login() {
        return login;
    }

    public void doLogin(String email, String password) {
        login.setValue(repository.login(email, password));
    }
}
