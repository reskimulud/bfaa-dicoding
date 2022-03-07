package com.mankart.myreadwritefile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mankart.myreadwritefile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNew.setOnClickListener(this)
        binding.buttonOpen.setOnClickListener(this)
        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            binding.buttonNew.id -> newFile()
            binding.buttonOpen.id -> showList()
            binding.buttonSave.id -> saveFile()
        }
    }

    private fun saveFile() {
        when {
            binding.editTitle.text.toString().isEmpty() -> showToast("Title harus diisi")
            binding.editFile.text.toString().isEmpty() -> showToast("Konten harus diisi")
            else -> {
                val title = binding.editTitle.text.toString()
                val text = binding.editFile.text.toString()
                val fileModel = FileModel()
                fileModel.filename = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel, this)
                showToast("Saving ${fileModel.filename} file")
            }
        }
    }

    private fun showList() {
        val items = fileList()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pilih file yang diinginkan")
        builder.setItems(items) { _, item ->
            loadData(items[item].toString())
        }
        builder.create().show()
    }

    private fun loadData(title: String) {
        val fileModel = FileHelper.readFromFile(this, title)
        binding.editTitle.setText(fileModel.filename)
        binding.editFile.setText(fileModel.data)
        showToast("Loading")
    }

    private fun newFile() {
        binding.editTitle.setText("")
        binding.editFile.setText("")
        showToast("Clearing File")
    }

    private fun showToast(message: String, isLong: Boolean = false) {
        Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }

}