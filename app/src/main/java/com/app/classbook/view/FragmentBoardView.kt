
package com.app.classbook.view

import com.app.classbook.model.response.AllClassesResponse
import com.app.classbook.model.response.BoardsResponse
import retrofit2.Response

interface FragmentBoardView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadDataBoard(token: String)
        fun onStop()
    }
}