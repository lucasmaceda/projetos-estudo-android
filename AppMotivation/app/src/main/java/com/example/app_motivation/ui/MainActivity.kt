package com.example.app_motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.app_motivation.infra.MotivationConstants
import com.example.app_motivation.R
import com.example.app_motivation.repository.Mock
import com.example.app_motivation.infra.SecurityPreferences
import com.example.app_motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences

    private var filter: Int = MotivationConstants.PHRASEFILTER.ALL
    private val mock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Remove a supportActionBar
        supportActionBar?.hide()

        // Inicializa variáveis
        securityPreferences = SecurityPreferences(this)

        // Adiciona eventos
        setListeners()

        // Inicializa
        handleFilter(R.id.image_all)
        refreshPhrase()
    }

    private fun setListeners() {
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    /**
     * Atualiza frase de motivação
     * */
    private fun refreshPhrase() {
        binding.textPhrase.text = mock.getPhrase(filter, Locale.getDefault().language)
    }

    /**
     * Busca o nome do usuário
     * */
    private fun showUserName() {
        val name = securityPreferences.getStoredString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_new_phrase -> refreshPhrase()
            R.id.image_all -> handleFilter(view.id)
            R.id.image_happy -> handleFilter(view.id)
            R.id.image_sunny -> handleFilter(view.id)
        }
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.PHRASEFILTER.SUNNY
            }
        }
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getStoredString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }
}