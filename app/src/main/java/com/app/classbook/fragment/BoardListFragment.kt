package com.app.classbook.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.activities.*
import com.app.classbook.adapter.BoardAdapter1
import com.app.classbook.model.response.BoardsData
import com.app.classbook.model.response.BoardsResponse
import com.app.classbook.presenter.FragmentBoardPresenter
import com.app.classbook.util.Utils.showLoginAlert
import com.app.classbook.view.FragmentBoardView
import kotlinx.android.synthetic.main.fragment_classes_list.*
import kotlinx.android.synthetic.main.fragment_classes_list.ivCart
import kotlinx.android.synthetic.main.fragment_classes_list.ivFav
import kotlinx.android.synthetic.main.fragment_classes_list.ivNotification
import kotlinx.android.synthetic.main.fragment_classes_list.ivSetting
import retrofit2.Response

class BoardListFragment : AppCompatActivity(), FragmentBoardView.MainView {

    private lateinit var presenter: FragmentBoardPresenter
    private lateinit var adapter: BoardAdapter1
    private lateinit var dataList: ArrayList<BoardsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_classes_list)
        init()
    }

    private fun init() {

        presenter = FragmentBoardPresenter(this, this)
        presenter.loadDataBoard(SharedPreference.authToken!!)
        llFilter.visibility = View.GONE
        val layoutManager = LinearLayoutManager(this)
        placeList1RecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = BoardAdapter1(dataList)
        placeList1RecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : BoardAdapter1.OnItemClickListener {
            override fun onItemClick(view: View, obj: BoardsData, position: Int) {
                startActivity(
                    Intent(
                        this@BoardListFragment, ClassDetailsActivity::class.java
                    ).putExtra("id", obj.id).putExtra("title", obj.name)
                )
            }
        })

        ivSetting.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        ivNotification.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }
        ivFav.setOnClickListener {
            startActivity(Intent(this, FavouriteActivity::class.java))
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

    override fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            dataList.clear()
            dataList.addAll(responseModel.body()!!.data)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                showLoginAlert(this)
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
