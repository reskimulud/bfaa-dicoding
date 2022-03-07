package com.mankart.mysharedpreferences

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null,
    var age: Int = 0,
    var isLove: Boolean = false
) : Parcelable
