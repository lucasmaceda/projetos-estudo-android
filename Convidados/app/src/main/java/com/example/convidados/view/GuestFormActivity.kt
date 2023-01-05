package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.constants.DatabaseConstants
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel
import com.example.convidados.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        binding.buttonSave.setOnClickListener(this)
        binding.radioPresence.isChecked = true

        loadData()
        observe()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked

            val model = GuestModel().apply {
                this.id = guestId
                this.name = name
                this.presence = presence
            }

            viewModel.save(model)
        }
    }

    private fun observe() {
        viewModel.guest.observe(this, Observer {
            binding.editName.setText(it.name)

            if (it.presence) {
                binding.radioPresence.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer {
            if (it) {
                if (guestId == 0)
                    Toast.makeText(applicationContext, "Inserção com sucesso", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(applicationContext, "Atualização com sucesso", Toast.LENGTH_SHORT).show()

                finish()
            } else {
              Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DatabaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

}