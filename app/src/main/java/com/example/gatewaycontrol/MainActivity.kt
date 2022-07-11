package com.example.gatewaycontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gatewaycontrol.api.RetrofitInstance
import com.example.gatewaycontrol.room.Remote
import com.example.gatewaycontrol.room.RemoteDB
import com.example.gatewaycontrol.utils.Constants.Companion.REMOTE_TYPE_RF
import com.example.gatewaycontrol.utils.Constants.Companion.REMOTE_TYPE_TV
import kotlinx.coroutines.*
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    private lateinit var remoteAdapter : ListRemoteAdapter
    val db by lazy { RemoteDB(this) }
    private val api = RetrofitInstance
//    private var listRemote: ArrayList<Remote> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        showRecyclerList()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val remote = db.remoteDao().getAllRemote()
            withContext(Dispatchers.Main){
                remoteAdapter.setData(remote)
            }
        }
    }

    private fun showRecyclerList() {
        remoteAdapter = ListRemoteAdapter(arrayListOf(), object : ListRemoteAdapter.onClickListener{
            override fun onClick(remote: Remote) {
                Log.d("LOG : ",remote.id.toString())
                Log.d("LOG : ",remote.remoteName.toString())
                Log.d("LOG : ",remote.remoteType.toString())
            }
        })
        val rv : RecyclerView = findViewById(R.id.rv)
        rv.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = remoteAdapter
        }

    }

    private fun sendIrSender() {
        val protocol = "BIOBIOP"
        val value = "011111111110"
        val length = "38"


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res = api.apiScarlar.sendIR(protocol, value, length).awaitResponse()
                if (res.isSuccessful) {
                    Toast.makeText(applicationContext, "Sended....!!", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO) {

                }

            }
        }
    }

    private fun sendRfSender() {
        val value = "10000000001"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res = api.apiScarlar.sendRF(value).awaitResponse()
                if (res.isSuccessful) {
                    Toast.makeText(applicationContext, "Sended....!!", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO) {

                }

            }
        }

    }

    private fun getRfReceiver() {

        CoroutineScope(Dispatchers.IO).launch {
            try {

                val res = api.apiScarlar.getRF().awaitResponse()
                if (res.isSuccessful) {
                    val data = res.body()
                    Log.d("Message", data.toString())
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO) {

                }

            }
        }
    }

    private fun getIrReceiver() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res = api.apiGson.getIR().awaitResponse()
                if (res.isSuccessful) {
                    val data = res.body()!!
                    Log.d("V21", data.v21)
                    Log.d("V22", data.v22)
                    Log.d("V23", data.v23)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO) {
                    Toast.makeText(applicationContext, "Something wrong!!!!", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selected: Int) {
        when (selected) {
            R.id.add_remote -> {
                startActivity(
                    Intent(this@MainActivity, AddRemoteActivity::class.java)
                )
//                listRemote.add(Remote(0,"REMOTE TEST TV" , REMOTE_TYPE_TV))
//                rvRemote.adapter!!.notifyDataSetChanged()
            }
            R.id.action_2 -> {
                Toast.makeText(this, "ACtion 2 ", Toast.LENGTH_SHORT).show()
//                listRemote.add(Remote(1,"REMOTE TEST RF" , REMOTE_TYPE_RF))
//                rvRemote.adapter!!.notifyDataSetChanged()
            }
            R.id.action_3 -> {
                Toast.makeText(this, "ACtion 3 Muehehe", Toast.LENGTH_SHORT).show()
//                listRemote.add(Remote(2,"REMOTE" , REMOTE_TYPE_RF))
//                rvRemote.adapter!!.notifyDataSetChanged()
            }
        }
    }
}