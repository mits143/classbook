package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.LearnAdapter
import com.app.classbook.adapter.LevelAdapter
import com.app.classbook.adapter.LevelAdapter1
import com.app.classbook.adapter.LevelAdapter2
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivityLearnEarnPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityLearnEarnView
import kotlinx.android.synthetic.main.activity_learn_earn.*
import kotlinx.android.synthetic.main.activity_learn_earn.recyclerView
import kotlinx.android.synthetic.main.activity_learn_earn.recyclerView1
import kotlinx.android.synthetic.main.activity_learn_earn.recyclerView2
import kotlinx.android.synthetic.main.activity_level_chart.*
import retrofit2.Response


class LearnEarn : AppCompatActivity(), ActivityLearnEarnView.MainView {

    private lateinit var presenter: ActivityLearnEarnPresenter
    private lateinit var adapter: LearnAdapter
    private lateinit var dataList: ArrayList<LearnEarnData>


    var levelAdapter: LevelAdapter? = null
    var levelAdapter1: LevelAdapter1? = null
    var levelAdapter2: LevelAdapter2? = null
    var allList: ArrayList<LevelDifferenceIncomeModel>? = arrayListOf()
    var allList1: ArrayList<ResidualIncomeModel>? = arrayListOf()
    var allList2: ArrayList<RoyaltyIncomeModel>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_earn)
        init()
    }

    private fun init() {

        presenter = ActivityLearnEarnPresenter(this, this)
        presenter.loadDataReg(SharedPreference.authToken!!)

        dataList = arrayListOf()
        adapter = LearnAdapter(dataList)
        recyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : LearnAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: LearnEarnData, position: Int) {
                startActivity(
                    Intent(this@LearnEarn, DetailActivity::class.java).putExtra("title", obj.title)
                        .putExtra("desc", obj.description)
                )
            }
        })

        levelAdapter = LevelAdapter(
            this!!,
            allList!!
        ) { allList -> onClick(allList) }

        recyclerView1.adapter = levelAdapter

        levelAdapter1 = LevelAdapter1(
            this!!,
            allList1!!
        ) { allList1 -> onClick(allList1) }

        recyclerView2.adapter = levelAdapter1

        levelAdapter2 = LevelAdapter2(
            this!!,
            allList2!!
        ) { allList2 -> onClick(allList2) }

        recyclerView3.adapter = levelAdapter2
        radioGrp.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                if (checkedId == R.id.rbSubscribe) {
                    recyclerView.visibility = View.VISIBLE
                    llLevel.visibility = View.GONE
                    presenter.loadDataReg(SharedPreference.authToken!!)
                }
                if (checkedId == R.id.rbSubscribe1) {
                    recyclerView.visibility = View.VISIBLE
                    llLevel.visibility = View.GONE
                    presenter.loadDataLearnEarn(SharedPreference.authToken!!)
                }
                if (checkedId == R.id.rbSubscribe2) {
                    recyclerView.visibility = View.GONE
                    llLevel.visibility = View.VISIBLE
                    presenter!!.loadData(
                        SharedPreference.authToken!!
                    )
                }
            }
        }

        ivBack.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    fun onClick(data: LevelDifferenceIncomeModel) {
    }

    fun onClick(data: ResidualIncomeModel) {
    }

    fun onClick(data: RoyaltyIncomeModel) {
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<LearnEarnResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            dataList.clear()
            dataList.addAll(responseModel.body()!!.data)
            adapter.notifyDataSetChanged()
        }
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