package com.example.apitest.network.entity

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @SerializedName("access") val access: String,
    @SerializedName("refresh") val refresh: String,
    @SerializedName("user") val uesr: User
)
