package com.app.classbook.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.activities.*
import com.app.classbook.adapter.ClassesAdapter
import com.app.classbook.model.response.AllClassesResponse
import com.app.classbook.model.response.AllClassesResponseItem
import com.app.classbook.pagination.RecyclerViewLoadMoreScroll
import com.app.classbook.presenter.FragmentTeacherPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.FragmentTeacherView
import kotlinx.android.synthetic.main.fragment_classes_list.*
import kotlinx.android.synthetic.main.fragment_teachers_list.*
import kotlinx.android.synthetic.main.fragment_teachers_list.bookLoading
import kotlinx.android.synthetic.main.fragment_teachers_list.filter
import kotlinx.android.synthetic.main.fragment_teachers_list.ivCart
import kotlinx.android.synthetic.main.fragment_teachers_list.ivFav
import kotlinx.android.synthetic.main.fragment_teachers_list.ivNotification
import kotlinx.android.synthetic.main.fragment_teachers_list.ivSetting
import kotlinx.android.synthetic.main.fragment_teachers_list.loader
import kotlinx.android.synthetic.main.fragment_teachers_list.placeList1RecyclerView
import kotlinx.android.synthetic.main.fragment_teachers_list.searchView
import kotlinx.android.synthetic.main.fragment_teachers_list.txtNoRecords
import retrofit2.Response

class TeachersListFragment : Fragment(), FragmentTeacherView.MainView,
    SearchView.OnQueryTextListener {

    private lateinit var presenter: FragmentTeacherPresenter
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

        return inflater.inflate(R.layout.fragment_teachers_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }


    private fun init() {
        searchView.setOnQueryTextListener(this)
        presenter = FragmentTeacherPresenter(context, this)
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
        placeList1RecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = ClassesAdapter(dataList)
        placeList1RecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : ClassesAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: AllClassesResponseItem, position: Int) {
                startActivity(
                    Intent(
                        requireContext(),
                        TeacherDetailsActivity::class.java
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
            startActivityForResult(Intent(requireActivity(), FilterActivity::class.java), 100)
        }
        initEndlessList()

    }

    private fun initEndlessList() {
        val layoutManager = placeList1RecyclerView.layoutManager
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
        placeList1RecyclerView.addOnScrollListener(scrollListener!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (data != null) {
                pageIndex = 1
                searchName = ""
                stateId = data.extras!!.getInt("stateID")
                cityId = data.extras!!.getInt("cityID")
                boardId = data.extras!!.getInt("boardid")
                mediumId = data.extras!!.getInt("mediumid")
                standardId = data.extras!!.getInt("stdid")

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

    override fun onDestroy() {
        super.onDestroy()
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


