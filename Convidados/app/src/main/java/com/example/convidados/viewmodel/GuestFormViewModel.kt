package com.example.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.convidados.model.GuestModel
import com.example.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository(application)

    private val _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    private val _guest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = _guest

    fun save(guest: GuestModel) {
        if(guest.id == 0)
         _saveGuest.value = repository.insert(guest)
        else
         _saveGuest.value = repository.update(guest)
    }

    fun get(id: Int) {
        _guest.value = repository.get(id)
    }

}