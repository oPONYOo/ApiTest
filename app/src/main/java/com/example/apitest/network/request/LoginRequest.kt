package com.example.apitest.network.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("id") val userId: String,
    @SerializedName("password") val userPw: String,
    @SerializedName("role") val role: String
)
