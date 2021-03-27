package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    var `data`: LoginData,
    @SerializedName("message")
    var message: String, // Login_Success
    @SerializedName("status")
    var status: Boolean // true
)