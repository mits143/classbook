package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CartListResponse(
        @SerializedName("data")
    val `data`: TransactionData,
        @SerializedName("message")
    val message: String
)