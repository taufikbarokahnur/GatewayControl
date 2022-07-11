package com.example.gatewaycontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.gatewaycontrol.room.Remote
import com.example.gatewaycontrol.room.RemoteDB
import com.example.gatewaycontrol.utils.Constants.Companion.REMOTE_TYPE_TV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRemoteActivity : AppCompatActivity() {

    private lateinit var rvRemote: RecyclerView
    val db by lazy { RemoteDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_remote)
        val button: Button = findViewById(R.id.button2)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.remoteDao().insertRemote(Remote("REMOTE TEST TV", REMOTE_TYPE_TV))
            }
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }


    }
}