package com.app.classbook.view

import com.app.classbook.model.response.CourseSubTopicResponse
import com.app.classbook.model.response.SubTopicResponse
import com.app.classbook.model.response.TopicSubTopicResponse
import retrofit2.Response

interface ActivityCourseSubTopicView {
    interface MainView {
        fun showProgressbar()
        fun hideProgressbar()
        fun onSuccess(responseModel: Response<CourseSubTopicResponse>)
        fun onError(errorCode: Int)
        fun onError(throwable: Throwable)
    }

    interface MainPresenter {
        fun loadData(
            token: String,
            topicId: Int,
            courseId: Int
        )

        fun onStop()
    }
}