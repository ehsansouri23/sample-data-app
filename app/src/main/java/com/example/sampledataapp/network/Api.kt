package com.example.sampledataapp.network

import com.example.sampledataapp.data.model.Address
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("/address")
    suspend fun getAddresses(): Response<List<Address>>

    @POST("/address")
    suspend fun addAddress(@Body address: Address): Response<Address>
}