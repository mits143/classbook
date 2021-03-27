package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CartCompleteDetail(
    @SerializedName("cartDetailModel")
    val cartDetailModel: List<CartDetailModel>,
    @SerializedName("classBookHandlingAmount")
    val classBookHandlingAmount: Int,
    @SerializedName("grandTotal")
    val grandTotal: Double,
    @SerializedName("gst")
    val gst: Double,
    @SerializedName("internetHandlingCharge")
    val internetHandlingCharge: Double,
    @SerializedName("totalPrice")
    val totalPrice: Double
)