package com.example.sampledataapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    const val BASE_URL = "http://stage.achareh.ir/api/karfarmas"

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("$BASE_URL/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: Api = retrofit().create(Api::class.java)
}