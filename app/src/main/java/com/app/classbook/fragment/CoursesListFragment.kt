package com.app.classbook.fragment

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
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
import com.app.classbook.adapter.CoursesAdapter
import com.app.classbook.model.response.CoursesListResponse
import com.app.classbook.model.response.CoursesListResponseItem
import com.app.classbook.pagination.RecyclerViewLoadMoreScroll
import com.app.classbook.presenter.FragmentCoursesPresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.FragmentCoursesView
import kotlinx.android.synthetic.main.fragment_courses_list.*
import kotlinx.android.synthetic.main.fragment_courses_list.bookLoading
import kotlinx.android.synthetic.main.fragment_courses_list.filter
import kotlinx.android.synthetic.main.fragment_courses_list.ivCart
import kotlinx.android.synthetic.main.fragment_courses_list.ivFav
import kotlinx.android.synthetic.main.fragment_courses_list.ivNotification
import kotlinx.android.synthetic.main.fragment_courses_list.ivSetting
import kotlinx.android.synthetic.main.fragment_courses_list.loader
import kotlinx.android.synthetic.main.fragment_courses_list.searchView
import kotlinx.android.synthetic.main.fragment_courses_list.txtNoRecords
import kotlinx.android.synthetic.main.fragment_teachers_list.*
import retrofit2.Response

class CoursesListFragment : Fragment(), FragmentCoursesView.MainView,
    SearchView.OnQueryTextListener {

    private lateinit var presenter: FragmentCoursesPresenter
    private lateinit var adapter: CoursesAdapter
    private lateinit var dataList: ArrayList<CoursesListResponseItem>

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

        return inflater.inflate(R.layout.fragment_courses_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        searchView.setOnQueryTextListener(this)
        presenter = FragmentCoursesPresenter(context, this)
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

        dataList = arrayListOf()
        val layoutManager = LinearLayoutManager(context)
        coursesRecyclerView!!.layoutManager = layoutManager
        dataList = arrayListOf()
        adapter = CoursesAdapter(dataList)
        coursesRecyclerView!!.adapter = adapter
        adapter.setOnItemClickListener(object : CoursesAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: CoursesListResponseItem, position: Int) {
                context!!.startActivity(
                    Intent(context, CourseDetailsActivity::class.java).putExtra(
                        "id",
                        obj.id
                    )
                )
            }
        })


        ivSetting.setOnClickListener {
            startActivity(Intent(requireActivity(), SettingsActivity::class.java))
        }
        ivNotification.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                Utils.getBasicDialog(context!!)
            else
                startActivity(Intent(requireActivity(), NotificationActivity::class.java))
        }
        ivFav.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                Utils.getBasicDialog(context!!)
            else
                startActivity(Intent(requireActivity(), FavouriteActivity::class.java))
        }
        ivCart.setOnClickListener {
            if (TextUtils.equals(SharedPreference.authToken, "Default"))
                Utils.getBasicDialog(context!!)
            else
                startActivity(Intent(requireActivity(), CartActivity::class.java))
        }
        filter.setOnClickListener {
            startActivityForResult(
                Intent(
                    requireActivity(),
                    FilterActivity::class.java
                ).putExtra("screen", "course"), 100
            )
        }
        initEndlessList()
    }

    private fun initEndlessList() {
        val layoutManager = coursesRecyclerView.layoutManager
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
        coursesRecyclerView.addOnScrollListener(scrollListener!!)
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

    override fun onSuccess(responseModel: Response<CoursesListResponse>) {
        if (responseModel.body() != null) {
            if (responseModel.body()!!.data.isNotEmpty()) {
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
