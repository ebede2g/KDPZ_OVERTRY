package com.example.kdpz_overtry.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Main_(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Float,
    val humidity: Float,
):Parcelable