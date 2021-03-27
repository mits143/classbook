package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class CoursesListResponseItem(
    @SerializedName("averageRating")
    val averageRating: Int,
    @SerializedName("classOrTeacherCityName")
    val classOrTeacherCityName: String,
    @SerializedName("classOrTeacherName")
    val classOrTeacherName: String,
    @SerializedName("classOrTeacherStateName")
    val classOrTeacherStateName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("establishmentDate")
    val establishmentDate: String,
    @SerializedName("favourite")
    val favourite: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("isFavourite")
    val isFavourite: Boolean,
    @SerializedName("live")
    val live: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("online")
    val online: Boolean,
    @SerializedName("physical")
    val physical: Boolean,
    @SerializedName("rating")
    val rating: Any
)