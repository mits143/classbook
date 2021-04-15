package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class TransactionData(
    @SerializedName("cartCompleteDetail")
    val cartCompleteDetail: CartCompleteDetail
)