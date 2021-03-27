package com.app.classbook.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
//import com.app.classbook.adapter.ClassesDetailAdapter
import com.app.classbook.model.response.ClassDetailResponse
import com.app.classbook.presenter.ActivityTeacherDetailPresenter
import com.app.classbook.util.ItemDivider
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityTeacherDetailView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_classes_details.*
import retrofit2.Response

class TeacherDetailsActivity : AppCompatActivity(), ActivityTeacherDetailView.MainView {

    private lateinit var presenter: ActivityTeacherDetailPresenter
//    private lateinit var adapter: ClassesDetailAdapter
//    private lateinit var dataList: ArrayList<ClassDetailModel>

    private var id = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classes_details)
        init()
    }

    private fun init() {
        presenter = ActivityTeacherDetailPresenter(this, this)
        if (intent.extras != null) {
            className.text = intent.extras!!.getString("title")
            id = intent.extras!!.getInt("id")
            presenter.loadData(SharedPreference.authToken!!, id)
        }

//        val itemDecoration = ItemDivider(this, ItemDivider.VERTICAL)
//        itemDecoration.setDividerColor(ContextCompat.getColor(this, R.color.md_grey_900))
//        recyclerViewCat.addItemDecoration(itemDecoration)
//        val layoutManager = LinearLayoutManager(this)
//        recyclerViewCat!!.layoutManager = layoutManager
//        dataList = arrayListOf()
//        adapter = ClassesDetailAdapter(dataList)
//        recyclerViewCat!!.adapter = adapter
//        adapter.setOnItemClickListener(object : ClassesDetailAdapter.OnItemClickListener {
//            override fun onItemClick(view: View, obj: ClassDetailModel, position: Int) {startActivity(
//                Intent(
//                    this@TeacherDetailsActivity,
//                    AppEducationList1Activity::class.java
//                ).putExtra("id", id)
//                    .putExtra("moduleId", 3)
//                    .putExtra("boardId", obj.boardId)
//                    .putExtra("mediumId", obj.mediumId)
//                    .putExtra("standardsId", obj.standardsId)
//            )
//            }
//        })

        btnBack.setOnClickListener {
            finish()
        }
        ivNotification.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
        ivFav.setOnClickListener {
            startActivity(Intent(this, FavouriteActivity::class.java))
        }
        favFloatingActionButton.setOnClickListener {
            if (!TextUtils.equals(SharedPreference.authToken, "Default"))
                presenter.postAddToFav(SharedPreference.authToken!!, id, "teacher")
            else
                Toast.makeText(this, "Please login to add in favorite", Toast.LENGTH_LONG).show()
        }
        ivCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
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

    override fun onSuccess(responseModel: Response<ClassDetailResponse>) {
        if (responseModel.body() != null) {
            phoneTextView.text = responseModel.body()!!.data.teacherName
            txtClassName.text = responseModel.body()!!.data.teacherName
            txtAddress.text = responseModel.body()!!.data.address
            txtEmail.text = responseModel.body()!!.data.email
            txtContact.text = responseModel.body()!!.data.contactNo
            txtWebsite.text = responseModel.body()!!.data.website
            txtDesc.text = responseModel.body()!!.data.description
            txtCuriculum.text = responseModel.body()!!.data.curriculum
//                websiteTextView.text = responseModel.body()!!.cityName
//                if (responseModel.body()!!.classDetailModels.isNotEmpty()) {
//                    dataList.clear()
//                    dataList.addAll(responseModel.body()!!.classDetailModels)
//                    adapter.notifyDataSetChanged()
//            }
        }
    }

    override fun onSuccessAddToFav(responseModel: Response<JsonObject>) {
        Toast.makeText(this, responseModel.body()!!.get("message").asString, Toast.LENGTH_SHORT)
            .show()
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


