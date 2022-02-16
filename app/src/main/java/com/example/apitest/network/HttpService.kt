package com.example.apitest.network

import com.example.apitest.network.request.GameRequest
import com.example.apitest.network.request.LoginRequest
import com.example.apitest.network.response.AssignmentListResponse
import com.example.apitest.network.response.GameResponse
import com.example.apitest.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface HttpService {
    companion object {
        private const val HEADERS_JSON = "Content-Type:application/json"
        private const val HEADERS_JSON2 = "X-PATH:ADMIN"
        private const val USER_LOGIN = "user/check/login"
        private const val GET_ASSIGNMENT = "assignment/get/list"
        private const val GET_GAME = "game/get/setting"
        private const val AUTHORIZATION = "Authorization"
    }
    @Headers(HEADERS_JSON, HEADERS_JSON2)
    @POST(USER_LOGIN)
    suspend fun userLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @Headers(HEADERS_JSON, HEADERS_JSON2)
    @POST(GET_ASSIGNMENT)
    suspend fun getAssignmentList(@Header(AUTHORIZATION) token: String): Response<AssignmentListResponse>

    @Headers(HEADERS_JSON, HEADERS_JSON2)
    @POST(GET_GAME)
    suspend fun getGameInfo(@Header(AUTHORIZATION) token: String, @Body gameRequest: GameRequest): Response<GameResponse>
}
