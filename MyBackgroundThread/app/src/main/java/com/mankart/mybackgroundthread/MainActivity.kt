package com.mankart.mybackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.mankart.mybackgroundthread.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnStart = binding.btnStart
        val tvStatus = binding.tvStatus

        btnStart.setOnClickListener {
            lifecycleScope.launch {
                // simlate process compressing
                for (i in 0..10) {
                    delay(500L)
                    val percentage = i * 10
                    withContext(Dispatchers.Main) {
                        // update UI Thread
                        if (percentage == 100) {
                            tvStatus.setText(R.string.task_completed)
                        } else {
                            tvStatus.text = String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
            }
        }
    }
}