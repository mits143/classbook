package com.app.classbook.model.response


import com.google.gson.annotations.SerializedName

data class LevelDifferenceIncomeModel(
    @SerializedName("currentLevel")
    val currentLevel: String,
    @SerializedName("incomePercentage")
    val incomePercentage: String,
    @SerializedName("promotionLevel")
    val promotionLevel: String,
    @SerializedName("target")
    val target: String
)