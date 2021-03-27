package com.app.classbook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.CommonAdapter
import com.app.classbook.adapter.StateAdapter
import com.app.classbook.model.response.StateResponse
import com.app.classbook.model.response.StateResponseItem
import com.app.classbook.presenter.ActivityEditProfilePresenter
import com.app.classbook.presenter.RegisterPresenter
import com.app.classbook.view.ActivityEditProfileView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Response
import java.io.File


class EditProfileActivity : AppCompatActivity(), ActivityEditProfileView.MainView {

    private lateinit var presenter: ActivityEditProfilePresenter
    private var file0: File? = null;

    var stateList: ArrayList<StateResponseItem> = arrayListOf()
    var stateID = 0

    var cityList: ArrayList<StateResponseItem> = arrayListOf()
    var cityID = 0

    var pincodeList: ArrayList<StateResponseItem> = arrayListOf()
    var pincodeID = 0

    private lateinit var boardAdapter: CommonAdapter
    var boardList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var mediumAdapter: CommonAdapter
    var mediumList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var standardAdapter: CommonAdapter
    var standardList: ArrayList<StateResponseItem> = arrayListOf()

    private var boardid = 0
    private var mediumid = 0
    private var stdid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_edit_text_edit_text_basic_activity)
    }

    fun init (){
        presenter = ActivityEditProfilePresenter(this, this)
        presenter.getStates(SharedPreference.authToken!!)
        presenter.getBoard(SharedPreference.authToken!!)
        presenter.getMedium(SharedPreference.authToken!!)
        presenter.getStandard(SharedPreference.authToken!!)
    }

    override fun showProgressbar() {
        
    }

    override fun hideProgressbar() {
        
    }

    override fun onSuccessCommonData(int: Int, responseModel: Response<StateResponse>) {
        if (responseModel.body() != null) {
            when (int) {
                1 -> {
                    stateList.clear()
                    stateList.addAll(responseModel.body()!!)
                    val adapter =
                        StateAdapter(this, android.R.layout.simple_list_item_1, stateList!!)
                    state.setAdapter(adapter)
                }
                2 -> {
                    cityList.clear()
                    cityList.addAll(responseModel.body()!!)
                    val adapter =
                        StateAdapter(this, android.R.layout.simple_list_item_1, cityList!!)
                    city.setAdapter(adapter)
                }
                3 -> {
                    pincodeList.clear()
                    pincodeList.addAll(responseModel.body()!!)
                    val adapter =
                        StateAdapter(this, android.R.layout.simple_list_item_1, pincodeList!!)
                    zipCode.setAdapter(adapter)
                }
                4 -> {
                    boardList.clear()
                    boardList.addAll(responseModel.body()!!)
                    boardAdapter.notifyDataSetChanged()
                }
                5 -> {
                    mediumList.clear()
                    mediumList.addAll(responseModel.body()!!)
                    mediumAdapter.notifyDataSetChanged()
                }
                6 -> {
                    standardList.clear()
                    standardList.addAll(responseModel.body()!!)
                    standardAdapter.notifyDataSetChanged()
                }
            }
        }

    }

    override fun onSuccess(responseModel: Response<JsonObject>) {
        
    }

    override fun onError(errorCode: Int) {
        
    }

    override fun onError(throwable: Throwable) {
        
    }
}
