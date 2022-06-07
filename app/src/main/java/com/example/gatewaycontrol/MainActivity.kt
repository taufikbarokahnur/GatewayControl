package com.example.gatewaycontrol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                Toast.makeText(this, "Action 2 Muehehe", Toast.LENGTH_SHORT).show()
            }
            R.id.action_3 -> {
                Toast.makeText(this, "ACtion 3 Muehehe", Toast.LENGTH_SHORT).show()
            }
        }
    }
}