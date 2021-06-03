package com.capstone.rangkayo.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "TABLE_NEWS")
@Parcelize
data class NewsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "news")
    val _id: String,

    val title: String,
    val media: String? = null,
    val summary: String? = null,
    val link: String
) : Parcelable

