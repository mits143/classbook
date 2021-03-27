package com.app.classbook.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.FavoriteAdapter
import com.app.classbook.model.response.FavoriteResponse
import com.app.classbook.model.response.FavoriteResponseItem
import com.app.classbook.presenter.ActivityFavoritePresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.ActivityFavoriteView
import kotlinx.android.synthetic.main.fragment_classes_fav.*
import retrofit2.Response

class TeachersFavFragment : Fragment(), ActivityFavoriteView.MainView {

    private lateinit var presenter: ActivityFavoritePresenter
    private lateinit var adapter: FavoriteAdapter
    private lateinit var dataList: ArrayList<FavoriteResponseItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_classes_fav, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {

        presenter = ActivityFavoritePresenter(context, this)
        presenter.loadData(SharedPreference.authToken!!)

        val layoutManager = LinearLayoutManager(context)
        placeList1RecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = FavoriteAdapter(dataList)
        placeList1RecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : FavoriteAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: FavoriteResponseItem, position: Int) {

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

    override fun onSuccess(responseModel: Response<FavoriteResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.isNotEmpty()) {
            dataList.clear()
            for (i in responseModel.body()!!.indices) {
                if (TextUtils.equals(responseModel.body()!![i].entityName, "Teacher")) {
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


