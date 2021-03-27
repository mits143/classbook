package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("authorizeTokenKey")
    var authorizeTokenKey: String, // FHhEHQbisEqgFCiToCS1NQ
    @SerializedName("deviceTokenCode")
    var deviceTokenCode: Any, // null
    @SerializedName("email")
    var email: String, // ra5881ke58s5h58girase6008@gmail.com
    @SerializedName("userId")
    var userId: Int // 8
)