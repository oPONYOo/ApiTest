package com.example.apitest.network.entity

import com.google.gson.annotations.SerializedName

data class GameInfo(
    @SerializedName("limit") val limit: Int,
    @SerializedName("level") val level: Int
)
