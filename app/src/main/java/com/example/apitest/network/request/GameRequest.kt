package com.example.apitest.network.request

import com.google.gson.annotations.SerializedName

data class GameRequest(
    @SerializedName("assignment") val assignment: String,
    @SerializedName("game") val game: String
)
