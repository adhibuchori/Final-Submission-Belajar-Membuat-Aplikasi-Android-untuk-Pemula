package com.adhibuchori.finalsubmission_simpleandroidapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val name: String,
    val category: String,
    val image: String,
    val information: String
) : Parcelable
