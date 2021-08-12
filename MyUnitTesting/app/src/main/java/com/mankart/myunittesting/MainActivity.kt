package com.mankart.myunittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var edWidth: EditText
    private lateinit var edHeight: EditText
    private lateinit var edLength: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalculateVolume: Button
    private lateinit var btnCalculateSurfaceArea: Button
    private lateinit var btnCalculateCircumference: Button
    private lateinit var btnSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = MainViewModel(CuboidModel())

        edWidth = findViewById(R.id.width)
        edHeight = findViewById(R.id.height)
        edLength = findViewById(R.id.length)

        tvResult = findViewById(R.id.tv_result)

        btnCalculateVolume = findViewById(R.id.btn_calculate_volume)
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area)
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference)
        btnSave = findViewById(R.id.btn_save)

        btnCalculateVolume.setOnClickListener(this)
        btnCalculateSurfaceArea.setOnClickListener(this)
        btnCalculateCircumference.setOnClickListener(this)
        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val length = edLength.text.toString().trim()
        val width = edWidth.text.toString().trim()
        val height = edHeight.text.toString().trim()

        when {
            length.isEmpty() -> edLength.error = "Field tidak boleh kosong !"
            width.isEmpty() -> edWidth.error = "Field tidak boleh kosoong !"
            height.isEmpty() -> edHeight.error = "Field tidak boleh kosong !"
            else -> {
                val l = length.toDouble()
                val w = width.toDouble()
                val h = height.toDouble()

                when {
                    v.id == R.id.btn_save -> {
                        mainViewModel.save(l, w, h)
                        visible()
                    }

                    v.id == R.id.btn_calculate_volume -> {
                        tvResult.text =  mainViewModel.getVolume().toString()
                        gone()
                    }

                    v.id == R.id.btn_calculate_surface_area -> {
                        tvResult.text = mainViewModel.getSurfaceArea().toString()
                        gone()
                    }

                    v.id == R.id.btn_calculate_circumference -> {
                        tvResult.text = mainViewModel.getCircumference().toString()
                        gone()
                    }
                }

            }
        }
    }

    private fun visible() {
        btnCalculateVolume.visibility = View.VISIBLE
        btnCalculateSurfaceArea.visibility = View.VISIBLE
        btnCalculateCircumference.visibility = View.VISIBLE

        btnSave.visibility = View.GONE
    }

    private fun gone() {
        btnCalculateVolume.visibility = View.GONE
        btnCalculateSurfaceArea.visibility = View.GONE
        btnCalculateCircumference.visibility = View.GONE

        btnSave.visibility = View.VISIBLE
    }
}