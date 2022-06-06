package com.example.lotteria.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    var title: String,
    var picture: String,
    var dateFormat: String,
    var tag: String?
): Parcelable
