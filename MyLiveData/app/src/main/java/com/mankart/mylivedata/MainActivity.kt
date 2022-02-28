package com.mankart.mylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mankart.mylivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mLiveDataTimerViewModel : MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribe()


    }

    private fun subscribe() {
        val elapsedTimeObserver = Observer<Long?> { along ->
            val newText = this@MainActivity.resources.getString(R.string.seconds, along)
            activityMainBinding.timerTextview.text = newText
        }

        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}