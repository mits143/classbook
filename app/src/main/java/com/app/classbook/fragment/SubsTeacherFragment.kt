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
import com.app.classbook.model.response.SubscriptionResponse
import com.app.classbook.model.response.SubscriptionResponseItem
import com.app.classbook.presenter.FragmentSubscriptionPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.FragmentSubscriptionView
import kotlinx.android.synthetic.main.subs_fragment.*
import retrofit2.Response


class SubsTeacherFragment : Fragment(), FragmentSubscriptionView.MainView {

    private lateinit var presenter: FragmentSubscriptionPresenter
    private lateinit var adapter: SubscriptionAdapter
    private lateinit var dataList: ArrayList<SubscriptionResponseItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.subs_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }


    private fun init() {

        presenter = FragmentSubscriptionPresenter(context, this)
        presenter.loadData(SharedPreference.authToken!!)

        val layoutManager = LinearLayoutManager(context)
        placeList1RecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = SubscriptionAdapter(dataList)
        placeList1RecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : SubscriptionAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: SubscriptionResponseItem, position: Int) {
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

    override fun onSuccess(responseModel: Response<SubscriptionResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.isNotEmpty()) {
            dataList.clear()
            for (i in responseModel.body()!!.indices) {
                if (TextUtils.equals(responseModel.body()!![i].providerType, "Teacher")) {
                    dataList.add(responseModel.body()!![i])
                }
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
