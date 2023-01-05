package com.example.convidados.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.convidados.model.GuestModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDatabase : RoomDatabase() {

    abstract fun guestDAO(): GuestDAO

    companion object {
        private lateinit var INSTANCE: GuestDatabase

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): GuestDatabase {
            if(!::INSTANCE.isInitialized) {
                synchronized(GuestDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDatabase::class.java, "guestDB")
                        .addMigrations()
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }
    }

}