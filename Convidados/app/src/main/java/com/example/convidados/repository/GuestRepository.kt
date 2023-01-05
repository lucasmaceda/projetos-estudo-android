package com.example.convidados.repository

import android.content.Context
import com.example.convidados.model.GuestModel

class GuestRepository(context: Context) {

    private val guestDatabase = GuestDatabase
                                    .getDatabase(context)
                                    .guestDAO()

    fun get(id: Int): GuestModel {
        return guestDatabase.get(id)
    }

    fun getAll(): List<GuestModel> {
        return guestDatabase.getAll()
    }

    fun getPresent(): List<GuestModel> {
        return guestDatabase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return guestDatabase.getAbsent()
    }

    fun insert(guest: GuestModel): Boolean {
        return guestDatabase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guestDatabase.update(guest) > 0
    }

    fun delete(id: Int) {
        val guest = get(id)
        guestDatabase.delete(guest)
    }

}