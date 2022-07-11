package com.example.gatewaycontrol.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Remote(
    var remoteName: String? = null,
    var remoteType: Int? = null
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
