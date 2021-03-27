package com.app.classbook.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.LevelAdapter
import com.app.classbook.adapter.LevelAdapter1
import com.app.classbook.adapter.LevelAdapter2
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivityLearnEarnPresenter
import com.app.classbook.presenter.LevelPresenter
import com.app.classbook.view.ActivityLearnEarnView
import com.app.classbook.view.LevelView
import kotlinx.android.synthetic.main.activity_level_chart.*
import retrofit2.Response


class LevelActivity : AppCompatActivity(), ActivityLearnEarnView.MainView {

    var presenter: ActivityLearnEarnPresenter? = null
    var levelAdapter: LevelAdapter? = null
    var levelAdapter1: LevelAdapter1? = null
    var levelAdapter2: LevelAdapter2? = null
    var allList: ArrayList<LevelDifferenceIncomeModel>? = arrayListOf()
    var allList1: ArrayList<ResidualIncomeModel>? = arrayListOf()
    var allList2: ArrayList<RoyaltyIncomeModel>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_level_chart)
        init()
    }

    fun init() {
        presenter = ActivityLearnEarnPresenter(this, this)
        presenter!!.loadData(
            SharedPreference.authToken!!
        )
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        levelAdapter = LevelAdapter(
            this!!,
            allList!!
        ) { allList -> onClick(allList) }

        recyclerView!!.adapter = levelAdapter

        val layoutManager1 = LinearLayoutManager(this)
        recyclerView1!!.layoutManager = layoutManager1
        levelAdapter1 = LevelAdapter1(
            this!!,
            allList1!!
        ) { allList1 -> onClick(allList1) }

        recyclerView1!!.adapter = levelAdapter1

        val layoutManager2 = LinearLayoutManager(this)
        recyclerView2!!.layoutManager = layoutManager2
        levelAdapter2 = LevelAdapter2(
            this!!,
            allList2!!
        ) { allList2 -> onClick(allList2) }

        recyclerView2!!.adapter = levelAdapter2
    }

    fun onClick(data: LevelDifferenceIncomeModel) {
    }

    fun onClick(data: ResidualIncomeModel) {
    }

    fun onClick(data: RoyaltyIncomeModel) {
    }

    override fun showProgressbar() {

    }

    override fun hideProgressbar() {

    }

    override fun onSuccess(responseModel: Response<LearnEarnResponse>) {
    }

    override fun onSuccessLevel(responseModel: Response<LevelResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.levelDifferenceIncomeModel.size > 0) {
            allList!!.clear()
            allList1!!.clear()
            allList2!!.clear()

            allList!!.addAll(responseModel.body()!!.data.levelDifferenceIncomeModel)
            allList1!!.addAll(responseModel.body()!!.data.residualIncomeModel)
            allList2!!.addAll(responseModel.body()!!.data.royaltyIncomeModel)
            levelAdapter!!.notifyDataSetChanged()
            levelAdapter1!!.notifyDataSetChanged()
            levelAdapter2!!.notifyDataSetChanged()

        }

    }

    override fun onError(errorCode: Int) {
        if (errorCode == 500) {
            Toast.makeText(this, getString(R.string.internal_server_error), Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

}
