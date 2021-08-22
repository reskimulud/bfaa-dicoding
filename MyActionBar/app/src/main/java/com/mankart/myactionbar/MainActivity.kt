package com.mankart.myactionbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.mankart.myactionbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, MenuFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            R.id.menu_2 -> {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                true
            }
            else -> true
        }
    }
}