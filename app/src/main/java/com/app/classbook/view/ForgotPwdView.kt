package com.app.classbook.view

import com.app.classbook.model.response.AllClassesResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ForgotPwdView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<JsonObject>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(email: String)
        fun onStop()
    }
}