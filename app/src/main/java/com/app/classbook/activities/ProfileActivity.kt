package com.app.classbook.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.BoardAdapter2
import com.app.classbook.adapter.CommonAdapter
import com.app.classbook.adapter.StateAdapter
import com.app.classbook.model.response.*
import com.app.classbook.presenter.ActivityProfilePresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityProfileView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.ivBack
import retrofit2.Response

class ProfileActivity : AppCompatActivity(), ActivityProfileView.MainView {

    private lateinit var presenter: ActivityProfilePresenter

    var stateList: ArrayList<StateResponseItem> = arrayListOf()

    var cityList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var boardAdapter: BoardAdapter2
    var boardList: ArrayList<BoardsData> = arrayListOf()

    private lateinit var mediumAdapter: CommonAdapter
    var mediumList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var standardAdapter: CommonAdapter
    var standardList: ArrayList<StateResponseItem> = arrayListOf()

    private var stateID = 0
    private var cityID = 0
    private var boardid = 0
    private var mediumid = 0
    private var stdid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
    }

    private fun init() {
        presenter = ActivityProfilePresenter(this, this)
        presenter.loadData(SharedPreference.authToken!!)
        presenter.getBoard(SharedPreference.authToken!!)
        presenter.getMedium(SharedPreference.authToken!!)
        presenter.getStandard(SharedPreference.authToken!!)

        ivBack.setOnClickListener {
            finish()
        }

        editFAB.setOnClickListener {
            editFAB.visibility = View.GONE
            AddressTextView.isFocusableInTouchMode = true
            websiteTextView.isFocusableInTouchMode = true
            textView27.isFocusableInTouchMode = true
            tvMedium.isFocusableInTouchMode = true
//            startActivity(Intent(this, EditProfileActivity::class.java))
        }
        websiteTextView.setOnClickListener {
            showBoardDialog()
        }
        textView27.setOnClickListener {
            showMediumDialog()
        }
        tvMedium.setOnClickListener {
            showsStandardDialog()
        }
    }

    private fun showBoardDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_recyclerview, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val rvDialog = dialogView.findViewById(R.id.rvDialog) as RecyclerView
        val btnDone = dialogView.findViewById(R.id.btnDone) as Button

        val layoutManager = LinearLayoutManager(this)
        rvDialog.layoutManager = layoutManager
        boardAdapter = BoardAdapter2(boardList)
        rvDialog.adapter = boardAdapter
        boardAdapter.setOnItemClickListener(object : BoardAdapter2.OnItemClickListener {
            override fun onItemClick(view: View, obj: BoardsData, position: Int) {
                boardid = obj.id
                board.setText(obj.name)
                dialog.dismiss()

            }
        })
        dialog.show()
    }

    private fun showMediumDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_recyclerview, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val rvDialog = dialogView.findViewById(R.id.rvDialog) as RecyclerView
        val btnDone = dialogView.findViewById(R.id.btnDone) as Button

        val layoutManager = LinearLayoutManager(this)
        rvDialog.layoutManager = layoutManager
        mediumAdapter = CommonAdapter(mediumList)
        rvDialog.adapter = mediumAdapter
        mediumAdapter.setOnItemClickListener(object : CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: StateResponseItem, position: Int) {
                mediumid = obj.id
                medium.setText(obj.name)
                dialog.dismiss()
            }
        })
        dialog.show()
    }

    private fun showsStandardDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_recyclerview, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val rvDialog = dialogView.findViewById(R.id.rvDialog) as RecyclerView
        val btnDone = dialogView.findViewById(R.id.btnDone) as Button

        val layoutManager = LinearLayoutManager(this)
        rvDialog.layoutManager = layoutManager
        standardAdapter = CommonAdapter(standardList)
        rvDialog.adapter = standardAdapter
        standardAdapter.setOnItemClickListener(object : CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: StateResponseItem, position: Int) {
                stdid = obj.id
                standard.setText(obj.name)
                dialog.dismiss()
            }
        })
        dialog.show()
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<ProfileResponse>) {
        if (responseModel.body() != null) {
//            tvClasses.text = responseModel.body()!!.data.boardName
//            tvCourses.text = responseModel.body()!!.data.mediumName
//            tvTeachers.text = responseModel.body()!!.data.standardName
            Glide.with(this).load(responseModel.body()!!.data.imageUrl).into(profileImageView)
            emailTextView.setText(responseModel.body()!!.data.email)
            phoneTextView.setText(responseModel.body()!!.data.contactNo)
            AddressTextView.setText(responseModel.body()!!.data.address)
            websiteTextView.setText(responseModel.body()!!.data.boardName)
            textView27.setText(responseModel.body()!!.data.mediumName)
            tvMedium.setText(responseModel.body()!!.data.standardName)
            tvDOB.setText(responseModel.body()!!.data.dob)
        }
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

    override fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            boardList.clear()
            boardList.addAll(responseModel.body()!!.data)
            boardAdapter.notifyDataSetChanged()
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Utils.showLoginAlert(this)
            }
            500 -> {
                Toast.makeText(this, getString(R.string.internal_server_error), Toast.LENGTH_SHORT)
                        .show()
            }
            else -> {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
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
