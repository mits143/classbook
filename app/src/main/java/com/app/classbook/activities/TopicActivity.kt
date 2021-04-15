package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.TopicAdapter
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivityTopicPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityTopicView
import kotlinx.android.synthetic.main.app_education_course_collection_list_2_activity.*
import kotlinx.android.synthetic.main.app_education_course_collection_list_2_activity.bookLoading
import kotlinx.android.synthetic.main.app_education_course_collection_list_2_activity.loader
import retrofit2.Response

class TopicActivity : AppCompatActivity(), ActivityTopicView.MainView {

    private lateinit var presenter: ActivityTopicPresenter
    private lateinit var adapter: TopicAdapter
    private lateinit var dataList: ArrayList<SubjectTopicData>

    lateinit var data: Subject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_education_course_collection_list_2_activity)
        init()
    }

    private fun init() {
        titleNameTextView.visibility = View.VISIBLE
        presenter = ActivityTopicPresenter(this, this)
        if (intent.extras != null) {
            data = intent.getSerializableExtra("data") as Subject //Obtaining data
            titleNameTextView.text =  data.subjectName
            presenter.loadData(SharedPreference.authToken!!, data.smbId, data.subjectId)
        }
        dataList = arrayListOf()
        val mLayoutManager = LinearLayoutManager(this)
        videoRecyclerView.layoutManager = mLayoutManager
        adapter = TopicAdapter(dataList)
        videoRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : TopicAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: SubjectTopicData, position: Int) {
                startActivity(
                    Intent(
                        this@TopicActivity,
                        SubTopic::class.java
                    ).putExtra("data", obj)
                )
            }
        })
        ivBack.setOnClickListener {
            finish()
        }
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<SubjectTopicResponse>) {
        if (responseModel.body() != null) {
            if (responseModel.body()!!.data.isNotEmpty()) {
                dataList.clear()
                dataList.addAll(responseModel.body()!!.data)
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


