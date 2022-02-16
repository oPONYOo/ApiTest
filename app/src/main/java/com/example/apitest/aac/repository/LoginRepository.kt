package com.example.apitest.aac.repository

import com.example.apitest.aac.BaseDataSource
import com.example.apitest.di.module.ApiService
import com.example.apitest.network.HttpService
import com.example.apitest.network.request.GameRequest
import com.example.apitest.network.request.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @ApiService private val httpService: HttpService
): BaseDataSource(){
    suspend fun userLogin(loginRequest: LoginRequest) = getResult { httpService.userLogin(loginRequest) }
    suspend fun getAssignmentList(token: String) = getResult { httpService.getAssignmentList(setToken(token)) }
    suspend fun getGame(token: String, gameRequest: GameRequest) = getResult { httpService.getGameInfo(setToken(token), gameRequest) }
}