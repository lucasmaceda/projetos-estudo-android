package com.example.login_projeto_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.login_projeto_mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLogin.setOnClickListener(this);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setObserver();
    }

    private void setObserver() {

        viewModel.welcome().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String it) {
                binding.textWelcome.setText(it);
            }
        });

        viewModel.login().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean it) {
                if (it) {
                    Toast.makeText(getApplicationContext(), "Sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Falha!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonLogin) {
            String email = binding.editEmail.toString();
            String password = binding.editPassword.toString();

            viewModel.doLogin(email, password);
        }
    }
}