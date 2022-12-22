package com.example.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.convidados.constants.DatabaseConstants

class GuestDatabase(context: Context)
    : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL("CREATE TABLE " + DatabaseConstants.GUEST.TABLE_NAME +"(" +
                            "" + DatabaseConstants.GUEST.COLUMNS.ID +" integer primary key autoincrement, " +
                            "" + DatabaseConstants.GUEST.COLUMNS.NAME +" text, " +
                            "" + DatabaseConstants.GUEST.COLUMNS.PRESENCE +" integer);")
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}