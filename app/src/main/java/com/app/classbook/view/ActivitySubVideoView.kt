package com.app.classbook.view

import com.app.classbook.model.response.SubTopicResponse
import com.app.classbook.model.response.SubjectVideoResponse
import com.app.classbook.model.response.TopicSubTopicResponse
import retrofit2.Response

interface ActivitySubVideoView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<SubjectVideoResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            topicId: Int,
            smbId: Int,
            subjectId: Int,
            subTopicId: Int
        )

        fun onStop()
    }
}