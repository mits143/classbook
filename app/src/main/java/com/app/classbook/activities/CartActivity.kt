package com.app.classbook.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.CartListAdapter
import com.app.classbook.model.response.CartDetailModel
import com.app.classbook.model.response.CartListResponse
import com.app.classbook.presenter.ActivityCartPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityCartView
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.cart_activity.*
import retrofit2.Response

class CartActivity : AppCompatActivity(), ActivityCartView.MainView {

    private lateinit var presenter: ActivityCartPresenter
    private lateinit var adapter: CartListAdapter
    private lateinit var dataList: ArrayList<CartDetailModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_activity)
        init()
    }

    private fun init() {
        presenter = ActivityCartPresenter(this, this)
        presenter.loadData(SharedPreference.authToken!!)

        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = CartListAdapter(dataList)
        recyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : CartListAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: CartDetailModel, position: Int) {
                presenter.loadData1(
                    SharedPreference.authToken!!,
                    obj.cartId,
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

    override fun onSuccess(responseModel: Response<CartListResponse>) {
        if (responseModel.body() != null) {
            if (responseModel.body()!!.data.cartCompleteDetail.cartDetailModel.isNotEmpty()) {
                txtNoRecords.visibility = View.GONE
                llCart.visibility = View.VISIBLE
                dataList.clear()
                dataList.addAll(responseModel.body()!!.data.cartCompleteDetail.cartDetailModel)
                adapter.notifyDataSetChanged()
            } else {
                dataList.clear()
                llCart.visibility = View.GONE
                txtNoRecords.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            }
            stripeDetailTextView5.text =
                responseModel.body()!!.data.cartCompleteDetail.gst.toString()
            stripeDetailTextView8.text =
                responseModel.body()!!.data.cartCompleteDetail.internetHandlingCharge.toString()
            stripeDetailTextView10.text =
                responseModel.body()!!.data.cartCompleteDetail.grandTotal.toString()
            stripeDetailTextView7.text =
                responseModel.body()!!.data.cartCompleteDetail.totalPrice.toString()
        } else {
            dataList.clear()
            adapter.notifyDataSetChanged()
        }
    }

    override fun onSuccessRemoveCart(responseModel: Response<JsonObject>) {
        presenter.loadData(SharedPreference.authToken!!)
        Toast.makeText(this, "Item removed from cart", Toast.LENGTH_LONG).show()
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