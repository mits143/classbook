package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cartCompleteDetail")
    val cartCompleteDetail: CartCompleteDetail
)