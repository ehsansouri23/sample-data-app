package com.example.sampledataapp.data

import android.util.Log
import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        val response: Response<T>
        return try {
            response = call.invoke()
            Log.e("ehsan", "${response.body()}")
            if (response.isSuccessful)
                Result.Success(response.body())
            else Result.Error(Exception("Response is not successful"))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}