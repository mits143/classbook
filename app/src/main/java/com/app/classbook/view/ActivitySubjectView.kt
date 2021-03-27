package com.app.classbook.view

import com.app.classbook.model.response.ClassDetailResponse
import com.app.classbook.model.response.SubjectResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ActivitySubjectView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<SubjectResponse>)
        fun onSuccessCart(responseModel: Response<JsonObject>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            Id: Int
        )
        fun addtocart(
            token: String,
            MappingId: Int,
            TypeOfMapping: String,
            Type: String
        )

        fun onStop()
    }
}