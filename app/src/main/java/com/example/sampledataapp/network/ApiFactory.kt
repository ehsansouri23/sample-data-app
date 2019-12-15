package com.example.sampledataapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    const val BASE_URL = "http://stage.achareh.ir/api/karfarmas"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Username", "09822222222")
                .addHeader("Password", "sana1234")
                .build()
            chain.proceed(request)
        }
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("$BASE_URL/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val api: Api = retrofit().create(Api::class.java)
}