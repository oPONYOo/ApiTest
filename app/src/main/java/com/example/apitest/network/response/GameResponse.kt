package com.example.apitest.network.response

import com.example.apitest.network.entity.GameInfo
import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("result") val result: GameInfo
): BaseResponse()
