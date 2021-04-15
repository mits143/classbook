package com.app.classbook.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.BoardAdapter2
import com.app.classbook.adapter.CommonAdapter
import com.app.classbook.adapter.StateAdapter
import com.app.classbook.model.response.*
import com.app.classbook.presenter.FilterPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.FilterView
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_filter.board
import kotlinx.android.synthetic.main.activity_filter.city
import kotlinx.android.synthetic.main.activity_filter.medium
import kotlinx.android.synthetic.main.activity_filter.standard
import kotlinx.android.synthetic.main.activity_filter.state
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Response


class FilterActivity : AppCompatActivity(), FilterView.MainView {

    private lateinit var presenter: FilterPresenter

    var stateList: ArrayList<StateResponseItem> = arrayListOf()

    var cityList: ArrayList<StateResponseItem> = arrayListOf()

    //    private lateinit var boardAdapter: CommonAdapter
    private lateinit var boardAdapter: BoardAdapter2
    var boardList: ArrayList<BoardsData> = arrayListOf()

    private lateinit var mediumAdapter: CommonAdapter
    var mediumList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var standardAdapter: CommonAdapter
    var standardList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var courseAdapter: CommonAdapter
    var courseList: ArrayList<StateResponseItem> = arrayListOf()

    private var stateID = 0
    private var cityID = 0
    private var boardid = 0
    private var mediumid = 0
    private var stdid = 0
    private var course_categoryid = 0
    private var categoryid = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        init()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        presenter = FilterPresenter(this, this)
        presenter.getStates(SharedPreference.authToken!!)
        presenter.getBoard(SharedPreference.authToken!!)
        presenter.getMedium(SharedPreference.authToken!!)
        presenter.getStandard(SharedPreference.authToken!!)
        presenter.getCourses(SharedPreference.authToken!!)

        if (intent.extras != null) {
            if (TextUtils.equals(intent.extras!!.getString("screen"), "course")){
                board.visibility = View.GONE
                medium.visibility = View.GONE
                standard.visibility = View.GONE
                course_Category.visibility = View.VISIBLE
                Category.visibility = View.GONE
            }else if (TextUtils.equals(intent.extras!!.getString("screen"), "expert")){
                board.visibility = View.GONE
                medium.visibility = View.GONE
                standard.visibility = View.GONE
                course_Category.visibility = View.GONE
                Category.visibility = View.VISIBLE
            }
        }

        state.setOnItemClickListener() { parent, _, position, id ->
            val selectedPoi = parent.adapter.getItem(position) as StateResponseItem?
            state.setText(selectedPoi?.name)
            stateID = selectedPoi?.id!!.toInt()
            presenter.getCities(SharedPreference.authToken!!, stateID)
        }

        state.setOnTouchListener { v, event ->
            state.showDropDown()
            false
        }

        city.setOnItemClickListener() { parent, _, position, id ->
            val selectedPoi = parent.adapter.getItem(position) as StateResponseItem?
            city.setText(selectedPoi?.name)
            cityID = selectedPoi?.id!!.toInt()
        }

        city.setOnTouchListener { v, event ->
            city.showDropDown()
            false
        }

        board.setOnClickListener {
            if (boardList.size > 0)
                showBoardDialog()
        }

        medium.setOnClickListener {
            if (mediumList.size > 0)
                showMediumDialog()
        }

        standard.setOnClickListener {
            if (standardList.size > 0)
                showsStandardDialog()
        }

        course_Category.setOnClickListener {
            if (courseList.size > 0)
                showsCourseCategoryDialog()
        }

        txtApply.setOnClickListener {
            val intent = Intent()
            intent.putExtra("stateID", stateID)
            intent.putExtra("cityID", cityID)
            intent.putExtra("boardid", boardid)
            intent.putExtra("mediumid", mediumid)
            intent.putExtra("stdid", stdid)
            intent.putExtra("course_categoryid", course_categoryid)
            intent.putExtra("categoryid", categoryid)
            setResult(100, intent)
            finish()
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

    private fun showsCourseCategoryDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_recyclerview, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val rvDialog = dialogView.findViewById(R.id.rvDialog) as RecyclerView
        val btnDone = dialogView.findViewById(R.id.btnDone) as Button

        val layoutManager = LinearLayoutManager(this)
        rvDialog.layoutManager = layoutManager
        courseAdapter = CommonAdapter(courseList)
        rvDialog.adapter = courseAdapter
        courseAdapter.setOnItemClickListener(object : CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: StateResponseItem, position: Int) {
                course_categoryid = obj.id
                course_Category.setText(obj.name)
                dialog.dismiss()
            }
        })
        dialog.show()
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
//                4 -> {
//                    boardList.clear()
//                    boardList.addAll(responseModel.body()!!)
//                    boardAdapter.notifyDataSetChanged()
//                }
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
                9 -> {
                    courseList.clear()
                    courseList.addAll(responseModel.body()!!)
                    courseAdapter.notifyDataSetChanged()
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