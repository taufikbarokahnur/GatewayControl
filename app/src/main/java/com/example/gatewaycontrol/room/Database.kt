package com.example.gatewaycontrol.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Remote::class],
    version = 1
)

abstract class RemoteDB : RoomDatabase() {
    abstract fun remoteDao(): RemoteDao

    companion object {

        @Volatile private var instance : RemoteDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RemoteDB::class.java,
            "remotedb.db"
        ).build()

    }
}