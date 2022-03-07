package com.mankart.mysharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.mankart.mysharedpreferences.databinding.ActivityFormUserPreferenceBinding

class FormUserPreferenceActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityFormUserPreferenceBinding
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_user_preference)

        binding = ActivityFormUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener(this)

        userModel = intent.getParcelableExtra<UserModel>("USER") as UserModel
        val formType = intent.getIntExtra(EXTRA_TYPE_FORM, 0)

        var actionBarTitle = ""
        var btnTitle = ""

        when (formType) {
            TYPE_ADD -> {
                actionBarTitle = "Tambah Data Baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionBarTitle = "Ubah Data"
                btnTitle = "Perbarui"
                showPreferenceInForm()
            }
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.btnSave.text = btnTitle
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showPreferenceInForm() {
        binding.apply {
            edtName.setText(userModel.name)
            edtAge.setText(userModel.age.toString())
            edtEmail.setText(userModel.email)
            edtPhone.setText(userModel.phoneNumber)
            if (userModel.isLove) {
                rbYes.isChecked = true
            } else {
                rbNo.isChecked = true
            }
        }
    }

    override fun onClick(view: View) {
        if (view.id == binding.btnSave.id) {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val age = binding.edtAge.text.toString().trim()
            val phoneNo = binding.edtPhone.text.toString().trim()
            val isLoveMu = binding.rgLoveMu.checkedRadioButtonId == binding.rbYes.id

            if (name.isEmpty()) {
                binding.edtName.error = FIELD_REQUIRED
                return
            }

            if (email.isEmpty()) {
                binding.edtEmail.error = FIELD_REQUIRED
                return
            }

            if (!isValidEmail(email)) {
                binding.edtEmail.error = FIELD_IS_NOT_VALID
                return
            }

            if (age.isEmpty()) {
                binding.edtAge.error = FIELD_REQUIRED
                return
            }

            if (phoneNo.isEmpty()) {
                binding.edtPhone.error = FIELD_REQUIRED
                return
            }

            if (!TextUtils.isDigitsOnly(phoneNo)) {
                binding.edtPhone.error = FIELD_DIGIT_ONLY
                return
            }

            saveUser(name, email, age, phoneNo, isLoveMu)

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, userModel)
            setResult(RESULT_CODE, resultIntent)

            finish()
        }
    }

    private fun saveUser(name: String, email: String, age: String, phoneNo: String, loveMu: Boolean) {
        val userPreference = UserPreferences(this)

        userModel.name = name
        userModel.email = email
        userModel.age = Integer.parseInt(age)
        userModel.phoneNumber = phoneNo
        userModel.isLove = loveMu

        userPreference.setUser(userModel)
        Toast.makeText(this, "Data tersimpan", Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2

        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanya boleh terisi numerik"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }
}