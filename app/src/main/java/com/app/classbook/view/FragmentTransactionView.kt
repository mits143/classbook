package com.app.classbook.view

import com.app.classbook.model.response.SubscriptionResponse
import com.app.classbook.model.response.TransactionResponse
import retrofit2.Response

interface FragmentTransactionView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<TransactionResponse>)
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