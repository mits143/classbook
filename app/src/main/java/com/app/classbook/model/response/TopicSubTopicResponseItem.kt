package com.app.classbook.model.response


import com.bignerdranch.expandablerecyclerview.model.Parent
import com.google.gson.annotations.SerializedName

class TopicSubTopicResponseItem : Parent<SubTopicResponseModel> {

    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("imageUrl")
    var imageUrl: String = ""

    @SerializedName("subTopicCount")
    var subTopicCount: Int = 0

    @SerializedName("subTopicResponseModel")
    var subTopicResponseModel: List<SubTopicResponseModel> = arrayListOf()


    override fun getChildList(): List<SubTopicResponseModel> {
        return subTopicResponseModel!!

    }
    override fun isInitiallyExpanded(): Boolean {
        return false
    }
}