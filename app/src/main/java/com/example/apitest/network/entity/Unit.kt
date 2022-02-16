package com.example.apitest.network.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Unit(
    @SerializedName("unit") val key: String,
    @SerializedName("title") val title: String,
    @SerializedName("code") val code: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("kindCode") val kindCode: String,
    @SerializedName("kind") val kind: String? = null,
    @SerializedName("section") val section: String,
    @SerializedName("category") val sectionDetail: String? = null,
    @SerializedName("progress") var progress: Int
): Parcelable
