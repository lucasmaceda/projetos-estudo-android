package com.example.app_motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.app_motivation.infra.MotivationConstants
import com.example.app_motivation.R
import com.example.app_motivation.databinding.ActivitySplashBinding
import com.example.app_motivation.infra.SecurityPreferences

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Inicializa variáveis da classe
        securityPreferences = SecurityPreferences(this)

        // Acesso aos elementos de interface)
        binding.buttonSave.setOnClickListener(this)
        verifyUserName()
    }

    /**
     * Tratamento de clicks dos elementos
     * */
    override fun onClick(view: View?) {
        val id: Int? = view?.id
        if (id == R.id.button_save) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getStoredString(MotivationConstants.KEY.USER_NAME)
        if(name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    /**
     * Salva o nome do usuário para utilizações futuras
     * */
    private fun handleSave() {

        // Obtém o nome
        val name: String = binding.editName.text.toString()

        // Verifica se usuário preencheu o nome
        if (name == "") {
            Toast.makeText(this, getString(R.string.validation_mandatory_name), Toast.LENGTH_LONG)
                .show()
        } else {
            // Salva os dados do usuário e redireciona para as frases
            securityPreferences.storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))

            // Impede que seja possível voltar a Activity
            finish()
        }
    }
}