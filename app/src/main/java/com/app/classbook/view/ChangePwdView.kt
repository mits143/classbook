package com.app.classbook.view

import com.app.classbook.model.response.AllClassesResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ChangePwdView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<JsonObject>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            oldPwd: String,
            newPwd: String
        )

        fun onStop()
    }
}