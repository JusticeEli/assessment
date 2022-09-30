package com.justice.shopmanagement.ui.news.models

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
@Entity(tableName = "articles")
@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
):Parcelable