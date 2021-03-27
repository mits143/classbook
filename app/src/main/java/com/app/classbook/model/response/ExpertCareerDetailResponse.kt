package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class ExpertCareerDetailResponse(
    @SerializedName("address")
    var address: String, // 46,Udhana Surat
    @SerializedName("alternateContact")
    var alternateContact: String, // 9909260584
    @SerializedName("cityName")
    var cityName: String, // Mumbai
    @SerializedName("contactNo")
    var contactNo: String, // 8141624468
    @SerializedName("description")
    var description: String, // Latest Class
    @SerializedName("dob")
    var dob: String, // 1994-10-08T00:00:00
    @SerializedName("email")
    var email: String, // rakes1h60118511111@gmail.com
    @SerializedName("firstName")
    var firstName: String, // Ishavar
    @SerializedName("gender")
    var gender: String, // Male
    @SerializedName("imageUrl")
    var imageUrl: Any, // null
    @SerializedName("lastName")
    var lastName: String, // Badgujar
    @SerializedName("pincode")
    var pincode: String, // 394210
    @SerializedName("referCode")
    var referCode: String, // Class101
    @SerializedName("stateName")
    var stateName: String, // Maharastra
    @SerializedName("teachingExperience")
    var teachingExperience: String // 1.2 years
)