package com.example.sampledataapp.data.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("region") val regsion: Int,
    @SerializedName("address") val address: String,
    @SerializedName("lat") val lat: Int,
    @SerializedName("lng") val long: Int,
    @SerializedName("coordinate_mobile") val mobile: Int,
    @SerializedName("coordinate_phone_number") val phone: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("gender") val gender: String
)