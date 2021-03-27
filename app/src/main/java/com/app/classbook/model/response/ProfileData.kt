package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

class ProfileData(
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("contactNo")
    var contactNo: String,
    @SerializedName("contactNoAlternate")
    var contactNoAlternate: String,
    @SerializedName("dob")
    var dob: String,
    @SerializedName("dateOfBirthString")
    var dateOfBirthString: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("aboutMe")
    var aboutMe: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("stateName")
    var stateName: String,
    @SerializedName("cityName")
    var cityName: String,
    @SerializedName("pincode")
    var pincode: String,
    @SerializedName("boardName")
    var boardName: String,
    @SerializedName("mediumName")
    var mediumName: String,
    @SerializedName("standardName")
    var standardName: String
)