package com.mankart.mynavigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mankart.mynavigationdrawer.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}