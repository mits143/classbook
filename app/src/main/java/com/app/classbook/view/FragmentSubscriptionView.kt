package com.app.classbook.view

import com.app.classbook.model.response.SubscriptionResponse
import retrofit2.Response

interface FragmentSubscriptionView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<SubscriptionResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String
        )
        fun onStop()
    }
}