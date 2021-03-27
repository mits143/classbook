package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.CoursesSubTopicAdapter
import com.app.classbook.adapter.SubTopicAdapter
import com.app.classbook.adapter.SubVideoAdapter
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivityCourseSubTopicPresenter
import com.app.classbook.presenter.ActivitySubTopicPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityCourseSubTopicView
import com.app.classbook.view.ActivitySubTopicView
import kotlinx.android.synthetic.main.app_education_course_collection_list_2_activity.*
import retrofit2.Response

class CourseSubTopic : AppCompatActivity(), ActivityCourseSubTopicView.MainView {

    private lateinit var presenter: ActivityCourseSubTopicPresenter
    private lateinit var adapter: CoursesSubTopicAdapter
    private lateinit var dataList: ArrayList<SubTopicModelX>

    lateinit var data: SubjectTopicData

    var courseId = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_education_course_collection_list_2_activity)
        init()
    }

    private fun init() {
        presenter = ActivityCourseSubTopicPresenter(this, this)
        if (intent.extras != null) {
            val topicid = intent.extras!!.getInt("topicid")
            courseId = intent.extras!!.getInt("courseId")
            presenter.loadData(
                SharedPreference.authToken!!,
                topicid,
                courseId
            )
        }
        titleNameTextView1.visibility = View.VISIBLE
        dataList = arrayListOf()
        val mLayoutManager = LinearLayoutManager(this)
        videoRecyclerView.layoutManager = mLayoutManager
        adapter = CoursesSubTopicAdapter(dataList)
        videoRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : CoursesSubTopicAdapter.OnItemClickListener {
            override fun onItemClick(
                view: View,
                obj: SubTopicModelX,
                position: Int
            ) {
                startActivity(
                    Intent(
                        this@CourseSubTopic,
                        CoursesVideoActivity::class.java
                    ).putExtra("topicId", obj.topicId)
                        .putExtra("courseId ", courseId)
                        .putExtra("subtopicId ", obj.subTopicId)
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

    override fun onSuccess(responseModel: Response<CourseSubTopicResponse>) {
        if (responseModel.body() != null) {
            if (responseModel.body()!!.data.topicListModel.isNotEmpty()) {
                dataList.clear()
                for (i in responseModel.body()!!.data.topicListModel.indices) {
                    dataList.addAll(responseModel.body()!!.data.topicListModel[i].subTopicListModel)
                }
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


