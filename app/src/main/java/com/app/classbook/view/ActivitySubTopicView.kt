package com.app.classbook.view

import com.app.classbook.model.response.SubTopicResponse
import com.app.classbook.model.response.TopicSubTopicResponse
import retrofit2.Response

interface ActivitySubTopicView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<SubTopicResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            smbId: Int,
            subjectId: Int,
            topicId: Int
        )

        fun onStop()
    }
}