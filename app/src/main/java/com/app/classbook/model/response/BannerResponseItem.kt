package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class BannerResponseItem(
    @SerializedName("id")
    var id: Int, // 6
    @SerializedName("imageUrl")
    var imageUrl: String, // https://classbookapplication.appspot.com/image/AdvertisementBanner/add 5.jpg
    @SerializedName("name")
    var name: String // Ad 5
)