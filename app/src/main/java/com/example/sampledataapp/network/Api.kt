package com.example.sampledataapp.network

import com.example.sampledataapp.data.model.Address
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/address")
    suspend fun getAddresses(): Response<List<Address>>
}