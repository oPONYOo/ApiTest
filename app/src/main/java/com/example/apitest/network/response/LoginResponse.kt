package com.example.apitest.network.response

import com.example.apitest.network.entity.LoginResult
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("result") val result: LoginResult? = null
): BaseResponse()
