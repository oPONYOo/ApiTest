package com.example.apitest.aac

import android.util.Log
import com.example.apitest.network.response.BaseResponse
import com.google.gson.Gson
import retrofit2.Response

abstract class BaseDataSource {

    protected fun setToken(token: String) = "Bearer $token"

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(response.errorBody()!!.string())
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return try {
            Log.e("remoteDataSource", message)
            val errorRes = Gson().fromJson(message, BaseResponse::class.java)
            val msg = errorRes.code
            Resource.error(msg.toString())
        } catch (e: Exception) {
            Resource.error(message)
        }
    }
}
