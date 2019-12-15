package com.example.sampledataapp.data

import com.example.sampledataapp.data.model.Address
import com.example.sampledataapp.network.Api

class Repository(private val api: Api) : BaseRepository() {

    suspend fun getAddresses(): Result<List<Address>> = safeApiCall { api.getAddresses() }
}