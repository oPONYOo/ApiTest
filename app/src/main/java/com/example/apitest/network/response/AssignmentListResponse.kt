package com.example.apitest.network.response

import com.example.apitest.network.entity.Assignment
import com.google.gson.annotations.SerializedName

data class AssignmentListResponse(
    @SerializedName("result") val result: ArrayList<Assignment>?
): BaseResponse()
