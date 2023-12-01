package com.dicoding.myhealth.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val token: String,
    val name: String,
    val userId: String
): Parcelable
