package com.app.classbook.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.LearnAdapter
import com.app.classbook.adapter.TrainigVideoAdapter
import com.app.classbook.model.response.LearnEarnData
import com.app.classbook.model.response.LearnEarnResponse
import com.app.classbook.model.response.TrainingVideo
import com.app.classbook.model.response.TrainingVideoResponse
import com.app.classbook.presenter.ActivityLearnEarnPresenter
import com.app.classbook.presenter.ActivityTrainigPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityTrainingView
import kotlinx.android.synthetic.main.activity_trainig_videos.*
import kotlinx.android.synthetic.main.activity_trainig_videos.ivBack
import kotlinx.android.synthetic.main.activity_trainig_videos.recyclerView
import retrofit2.Response

class TrainigVideos : AppCompatActivity(), ActivityTrainingView.MainView {

    private lateinit var presenter: ActivityTrainigPresenter
    private lateinit var adapter: TrainigVideoAdapter
    private lateinit var dataList: ArrayList<TrainingVideo>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainig_videos)
        init()
    }

    private fun init() {

        presenter = ActivityTrainigPresenter(this, this)
        presenter.loadDataReg(SharedPreference.authToken!!)

        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = TrainigVideoAdapter(dataList)
        recyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : TrainigVideoAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: TrainingVideo, position: Int) {

            }
        })
//        radioGrp.setOnCheckedChangeListener { group, checkedId ->
//            val rb = group.findViewById<View>(checkedId) as RadioButton
//            if (checkedId > -1) {
//                if (checkedId == R.id.rbSubscribe) {
//                    presenter.loadDataReg(SharedPreference.authToken!!)
//                }
//                if (checkedId == R.id.rbSubscribe1) {
//                    presenter.loadDataLearnEarn(SharedPreference.authToken!!)
//                }
//            }
//        }

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

    override fun onSuccess(responseModel: Response<TrainingVideoResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.trainingVideoList.isNotEmpty()) {
            dataList.clear()
            dataList.addAll(responseModel.body()!!.data.trainingVideoList)
            adapter.notifyDataSetChanged()
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