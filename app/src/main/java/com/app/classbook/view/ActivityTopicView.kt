package com.app.classbook.view

import com.app.classbook.model.response.SubjectTopicResponse
import com.app.classbook.model.response.TopicSubTopicResponse
import retrofit2.Response

interface ActivityTopicView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<SubjectTopicResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            smbid: Int,
            subjectId: Int
        )

        fun onStop()
    }
}