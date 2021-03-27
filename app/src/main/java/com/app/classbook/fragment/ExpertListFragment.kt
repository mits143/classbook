package com.app.classbook.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.activities.*
import com.app.classbook.adapter.ClassesAdapter
import com.app.classbook.model.response.AllClassesResponse
import com.app.classbook.model.response.AllClassesResponseItem
import com.app.classbook.pagination.RecyclerViewLoadMoreScroll
import com.app.classbook.presenter.FragmentExpertsPresenter
import com.app.classbook.util.Utils.showLoginAlert
import com.app.classbook.view.FragmentExpertsView
import kotlinx.android.synthetic.main.fragment_expert_list.*
import kotlinx.android.synthetic.main.fragment_expert_list.bookLoading
import kotlinx.android.synthetic.main.fragment_expert_list.filter
import kotlinx.android.synthetic.main.fragment_expert_list.ivCart
import kotlinx.android.synthetic.main.fragment_expert_list.ivFav
import kotlinx.android.synthetic.main.fragment_expert_list.ivNotification
import kotlinx.android.synthetic.main.fragment_expert_list.ivSetting
import kotlinx.android.synthetic.main.fragment_expert_list.loader
import retrofit2.Response

class ExpertListFragment : Fragment(), FragmentExpertsView.MainView,
    SearchView.OnQueryTextListener {

    private lateinit var presenter: FragmentExpertsPresenter
    private lateinit var adapter: ClassesAdapter
    private lateinit var dataList: ArrayList<AllClassesResponseItem>

    var boardId = 0
    var cityId = 0
    var classId = 0
    var courseCategoryId = 0
    var expertiesId = 0
    var mediumId = 0
    var onlineLive = false
    var onlineLivePhysical = false
    var pageIndex = 1
    var pageSize = 10
    var searchName = ""
    var standardId = 0
    var stateId = 0
    var teacherId = 0
    private var scrollListener: RecyclerViewLoadMoreScroll? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expert_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }


    private fun init() {
        searchView.setOnQueryTextListener(this)
        presenter = FragmentExpertsPresenter(context, this)
        presenter.loadData(
            SharedPreference.authToken!!,
            boardId,
            cityId,
            classId,
            courseCategoryId,
            expertiesId,
            mediumId,
            onlineLive,
            onlineLivePhysical,
            pageIndex,
            pageSize,
            searchName,
            standardId,
            stateId,
            teacherId
        )

        val layoutManager = LinearLayoutManager(context)
        expertList1RecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = ClassesAdapter(dataList)
        expertList1RecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : ClassesAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: AllClassesResponseItem, position: Int) {
                startActivity(
                    Intent(
                        requireContext(),
                        ExpertDetailsActivity::class.java
                    ).putExtra("id", obj.id).putExtra("title", obj.name)
                )
            }
        })

        ivSetting.setOnClickListener {
            startActivity(Intent(requireActivity(), SettingsActivity::class.java))
        }
        ivNotification.setOnClickListener {
            startActivity(Intent(requireActivity(), NotificationActivity::class.java))
        }
        ivFav.setOnClickListener {
            startActivity(Intent(requireActivity(), FavouriteActivity::class.java))
        }
        ivCart.setOnClickListener {
            startActivity(Intent(requireActivity(), CartActivity::class.java))
        }
        filter.setOnClickListener {
            startActivityForResult(
                Intent(
                    requireActivity(),
                    FilterActivity::class.java
                ).putExtra("screen", "expert"), 100
            )
        }

        initEndlessList()
    }

    private fun initEndlessList() {
        val layoutManager = expertList1RecyclerView.layoutManager
        scrollListener = RecyclerViewLoadMoreScroll(layoutManager!!)
        scrollListener!!.setOnLoadMoreListener {
            pageIndex++
            presenter.loadData(
                SharedPreference.authToken!!,
                boardId,
                cityId,
                classId,
                courseCategoryId,
                expertiesId,
                mediumId,
                onlineLive,
                onlineLivePhysical,
                pageIndex,
                pageSize,
                searchName,
                standardId,
                stateId,
                teacherId
            )
        }
        expertList1RecyclerView.addOnScrollListener(scrollListener!!)
    }

    override fun showProgressbar() {
        loader.visibility = View.VISIBLE
        bookLoading.start()
    }

    override fun hideProgressbar() {
        loader.visibility = View.GONE
        bookLoading.stop()
    }

    override fun onSuccess(responseModel: Response<AllClassesResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            if (scrollListener!!.loaded) {
                scrollListener!!.setLoaded()
            }
            if (pageIndex == 1) {
                if (responseModel.body()!!.data.isNotEmpty()) {
                    txtNoRecords.visibility = View.GONE
                    dataList.clear()
                    dataList.addAll(responseModel.body()!!.data)
                    adapter.notifyDataSetChanged()
                } else {
                    dataList.clear()
                    adapter.notifyDataSetChanged()
                    txtNoRecords.visibility = View.VISIBLE
                }
            } else {
                if (responseModel.body()!!.data.isNotEmpty()) {
                    dataList.addAll(responseModel.body()!!.data)
                    adapter.notifyDataSetChanged()
                }
            }
        } else {
            txtNoRecords.visibility = View.VISIBLE
            dataList.clear()
            adapter.notifyDataSetChanged()
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                showLoginAlert(requireActivity())
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

    override fun onQueryTextSubmit(query: String): Boolean {
        pageIndex = 1
        searchName = query
        presenter.loadData(
            SharedPreference.authToken!!,
            boardId,
            cityId,
            classId,
            courseCategoryId,
            expertiesId,
            mediumId,
            onlineLive,
            onlineLivePhysical,
            pageIndex,
            pageSize,
            searchName,
            standardId,
            stateId,
            teacherId
        )
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {

        return false
    }
}

