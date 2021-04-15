package com.app.classbook.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.SubscriptionAdapter
import com.app.classbook.adapter.TransactionAdapter
import com.app.classbook.model.response.SubscriptionResponse
import com.app.classbook.model.response.TransactionResponse
import com.app.classbook.model.response.TransactionResponseItem
import com.app.classbook.presenter.FragmentTransactionPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.FragmentTransactionView
import kotlinx.android.synthetic.main.transaction_fragment.*
import kotlinx.android.synthetic.main.transaction_fragment.bookLoading
import kotlinx.android.synthetic.main.transaction_fragment.loader
import kotlinx.android.synthetic.main.transaction_fragment.placeList1RecyclerView
import retrofit2.Response


class TransactionCoursesFragment : Fragment(), FragmentTransactionView.MainView {

    private lateinit var presenter: FragmentTransactionPresenter
    private lateinit var adapter: TransactionAdapter
    private lateinit var dataList: ArrayList<TransactionResponseItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.transaction_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        presenter = FragmentTransactionPresenter(context, this)
        presenter.loadData(SharedPreference.authToken!!)

        val layoutManager = LinearLayoutManager(context)
        placeList1RecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = TransactionAdapter(dataList)
        placeList1RecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : TransactionAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: TransactionResponseItem, position: Int) {
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

    override fun onSuccess(responseModel: Response<TransactionResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.isNotEmpty()) {
            dataList.clear()
            for (i in responseModel.body()!!.indices) {
                if (TextUtils.equals(responseModel.body()!![i].providerType, "Course")) {
                    dataList.add(responseModel.body()!![i])
                }
            }

            if (dataList.isEmpty()) {
                txtNoRecords.visibility = View.VISIBLE
            }else{
                txtNoRecords.visibility = View.GONE
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Utils.showLoginAlert(requireActivity())
            }
            500 -> {
                Toast.makeText(
                        requireActivity(),
                        getString(R.string.internal_server_error),
                        Toast.LENGTH_SHORT
                )
                        .show()
            }
            else -> {
                Toast.makeText(requireActivity(), getString(R.string.error), Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onStop()
    }
}
