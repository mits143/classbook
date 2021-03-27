package com.app.classbook.view

import com.app.classbook.model.response.StateResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

interface ActivityEditProfileView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccessCommonData(int: Int, responseModel: Response<StateResponse>)
        fun onSuccess(responseModel: Response<JsonObject>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun getStates(
            token: String
        )

        fun getCities(
            token: String, id: Int
        )

        fun getPincodes(
            token: String, id: Int
        )

        fun getBoard(
            token: String
        )

        fun getMedium(
            token: String
        )

        fun getStandard( token: String)
        fun loadData(
            token: String,
            file: MultipartBody.Part,
            data: RequestBody
        )
        fun onStop()
    }
}