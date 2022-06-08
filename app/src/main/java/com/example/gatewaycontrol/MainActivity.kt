package com.example.gatewaycontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.gatewaycontrol.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    private val api = RetrofitInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn: Button = findViewById(R.id.button)
        val btn2: Button = findViewById(R.id.button2)
        val btn3: Button = findViewById(R.id.button3)
        val btn4: Button = findViewById(R.id.button4)

        btn.setOnClickListener {
            getIrReceiver()

        }

        btn2.setOnClickListener{
            getRfReceiver()
        }

        btn3.setOnClickListener{
            sendRfSender()
        }

        btn4.setOnClickListener {
            sendIrSender()
        }


    }

    private fun sendIrSender() {
        val protocol = "BIOBIOP"
        val value = "011111111110"
        val length = "38"


        GlobalScope.launch(Dispatchers.IO) {
            try {
                val res = api.apiScarlar.sendIR(protocol,value,length).awaitResponse()
                if (res.isSuccessful) {
                    Toast.makeText(applicationContext, "Sended....!!", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO){

                }

            }
        }
    }

    private fun sendRfSender() {
        val value = "10000000001"

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val res = api.apiScarlar.sendRF(value).awaitResponse()
                if (res.isSuccessful) {
                    Toast.makeText(applicationContext, "Sended....!!", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO){

                }

            }
        }

    }

    private fun getRfReceiver() {

        GlobalScope.launch(Dispatchers.IO) {
            try {

                val res = api.apiScarlar.getRF().awaitResponse()
                if (res.isSuccessful) {
                    val data = res.body()
                    Log.d("Message", data.toString())
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO){

                }

            }
        }
    }

    private fun getIrReceiver() {

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val res = api.apiGson.getIR().awaitResponse()
                if (res.isSuccessful) {
                    val data = res.body()!!
                    Log.d("V21", data.v21)
                    Log.d("V22", data.v22)
                    Log.d("V23", data.v23)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.IO){
                    Toast.makeText(applicationContext, "Something wrong!!!!", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(this, "Add Remote Muehehe", Toast.LENGTH_SHORT).show()
            }
            R.id.action_2 -> {


            }
            R.id.action_3 -> {
                Toast.makeText(this, "ACtion 3 Muehehe", Toast.LENGTH_SHORT).show()
            }
        }
    }
}