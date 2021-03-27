package com.app.classbook.view

import com.app.classbook.model.response.CartListResponse
import com.google.gson.JsonObject
import retrofit2.Response

interface ActivityCartView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<CartListResponse>)
        fun onSuccessRemoveCart(responseModel: Response<JsonObject>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String
        )
        fun loadData1(
            token: String,
            cartItemId: Int
        )
        fun onStop()
    }
}