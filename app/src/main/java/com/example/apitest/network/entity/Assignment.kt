package com.example.apitest.network.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Assignment(
    @SerializedName("assignment") val key: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("group") val group: String? = null,
    @SerializedName("start") val start: String? = null,
    @SerializedName("end") val end:  String? = null,
    @SerializedName("path") val path: String? = null,
    @SerializedName("units") val units: ArrayList<Unit>? = null
): Parcelable
