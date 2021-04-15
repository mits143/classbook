package com.app.classbook.view

import com.app.classbook.model.response.BoardsResponse
import com.app.classbook.model.response.ProfileResponse
import com.app.classbook.model.response.StateResponse
import retrofit2.Response

interface ActivityProfileView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<ProfileResponse>)
        fun onSuccessCommonData(int: Int, responseModel: Response<StateResponse>)
        fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>)
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

        fun getBoard(
                token: String
        )

        fun getMedium(
                token: String
        )

        fun getStandard(token: String)

        fun loadData(
            token: String
        )
        fun onStop()
    }
}