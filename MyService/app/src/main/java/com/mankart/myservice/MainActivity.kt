package com.mankart.myservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mankart.myservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnStartService = binding.btnStartService
        val btnStartJobIntentService = binding.btnStartJobIntentService
        val btnStartBoundService = binding.btnStartBoundService
        val btnStopBoundService = binding.btnStopBoundService

        btnStartService.setOnClickListener {
            val mStartServiceIntent = Intent(this, MyService::class.java)
            startService(mStartServiceIntent)
        }
        btnStartJobIntentService.setOnClickListener {  }
        btnStartBoundService.setOnClickListener {  }
        btnStopBoundService.setOnClickListener {  }
    }
}