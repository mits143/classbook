package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.CourseTopicAdapter
import com.app.classbook.model.response.CourseDetailResponse
import com.app.classbook.model.response.TopicModelX
import com.app.classbook.presenter.ActivityCourseDetailPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityCourseDetailView
import kotlinx.android.synthetic.main.activity_course_details.*
import retrofit2.Response

class CourseDetailsActivity : AppCompatActivity(), ActivityCourseDetailView.MainView {

    private lateinit var presenter: ActivityCourseDetailPresenter
    private lateinit var adapter: CourseTopicAdapter
    private lateinit var dataList: ArrayList<TopicModelX>

    var courseId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_details)
        init()
    }

    private fun init() {
        presenter = ActivityCourseDetailPresenter(this, this)
        if (intent.extras != null) {
            val id = intent.extras!!.getInt("id")
            presenter.loadData(SharedPreference.authToken!!, id)
        }
        dataList = arrayListOf()
        adapter = CourseTopicAdapter(dataList)
        videoRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : CourseTopicAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: TopicModelX, position: Int) {
                startActivity(
                    Intent(
                        this@CourseDetailsActivity,
                        CourseSubTopic::class.java
                    ).putExtra("topicid", obj.topicId)
                        .putExtra("courseId", courseId)
                )
            }
        })
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<CourseDetailResponse>) {
        if (responseModel.body() != null) {
            titleTextView.text = responseModel.body()!!.data.courseName
            courseId = responseModel.body()!!.data.courseId
            if (responseModel.body()!!.data.topicListModel.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(responseModel.body()!!.data.topicListModel)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Utils.showLoginAlert(this)
            }
            500 -> {
                Toast.makeText(
                    this,
                    getString(R.string.internal_server_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            else -> {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }
}
