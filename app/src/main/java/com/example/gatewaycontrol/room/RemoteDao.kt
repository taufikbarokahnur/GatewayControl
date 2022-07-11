package com.example.gatewaycontrol.room

import androidx.room.*

@Dao
interface RemoteDao {
    @Query("SELECT * FROM remote")
    fun getAllRemote(): List<Remote>

    @Insert
    fun insertRemote(remote: Remote)

    @Update
    fun updateRemote(remote: Remote)

    @Delete
    fun deleteRemote(remote: Remote)
}