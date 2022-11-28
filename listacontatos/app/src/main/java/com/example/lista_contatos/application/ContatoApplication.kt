package com.example.lista_contatos.application

import android.app.Application

class ContatoApplication : Application() {

    var helperDB: HelperDB? = null
        private set

    companion object {
        lateinit var instance: ContatoApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperDB = HelperDB(this)
    }

}