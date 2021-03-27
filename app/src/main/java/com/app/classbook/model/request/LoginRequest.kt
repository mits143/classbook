package com.app.classbook.model.request


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("DeviceId")
    var DeviceId: String,
    @SerializedName("email")
    var email: String, // ra5881ke58s5h58girase6008@gmail.com
    @SerializedName("FCMId")
    var FCMId: String,
    @SerializedName("password")
    var password: String // 0vAU0C5JpDhBE8Rw
)