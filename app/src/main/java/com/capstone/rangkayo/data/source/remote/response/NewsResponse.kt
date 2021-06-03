package com.capstone.rangkayo.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(
    val _id: String,
    val title: String,
    val media: String? = null,
    val summary: String? = null,
    val link: String
) : Parcelable
