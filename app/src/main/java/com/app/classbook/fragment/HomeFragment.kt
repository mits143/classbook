package com.app.classbook.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.activities.*
import com.app.classbook.adapter.*
import com.app.classbook.model.response.*
import com.app.classbook.presenter.FragmentHomePresenter
import com.app.classbook.util.Utils
import com.app.classbook.view.FragmentHomeView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.ivCart
import kotlinx.android.synthetic.main.fragment_home.ivFav
import kotlinx.android.synthetic.main.fragment_home.ivNotification
import kotlinx.android.synthetic.main.fragment_home.ivSetting
import retrofit2.Response
import java.lang.IllegalStateException

class HomeFragment : Fragment(), FragmentHomeView.MainView {

    private lateinit var presenter: FragmentHomePresenter
    private lateinit var adapterBanner: BannerAdapter
    private lateinit var adapterCategories: HomeAdapter
    private lateinit var adapterBoards: BoardAdapter
    private lateinit var adapterClasses: HomeAdapter
    private lateinit var adapterTeachers: HomeAdapter
    private lateinit var adapterCourses: CourseHomeAdapter
    private lateinit var adapterExperts: HomeAdapter
    private lateinit var dataListBanner: ArrayList<BannerResponseItem>
    private lateinit var dataListBoards: ArrayList<BoardsData>
    private lateinit var dataListCategories: ArrayList<AllClassesResponseItem>
    private lateinit var dataListClasess: ArrayList<AllClassesResponseItem>
    private lateinit var dataLisTeachers: ArrayList<AllClassesResponseItem>
    private lateinit var dataListCourses: ArrayList<CoursesListResponseItem>
    private lateinit var dataListExperts: ArrayList<AllClassesResponseItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {

        presenter = FragmentHomePresenter(context, this)
        presenter.loadDataBanner(SharedPreference.authToken!!)
        presenter.loadDataBoard(SharedPreference.authToken!!)
        presenter.loadDataClasses(
            SharedPreference.authToken!!,
            0,
            0,
            0,
            0,
            0,
            0,
            false,
            false,
            0,
            10,
            "",
            0,
            0,
            0
        )
        presenter.loadDataTeacher(
            SharedPreference.authToken!!,
            0,
            0,
            0,
            0,
            0,
            0,
            false,
            false,
            0,
            10,
            "",
            0,
            0,
            0
        )
        presenter.loadDataCourses(
            SharedPreference.authToken!!,
            0,
            0,
            0,
            0,
            0,
            0,
            false,
            false,
            0,
            10,
            "",
            0,
            0,
            0
        )
        presenter.loadDataExperts(
            SharedPreference.authToken!!,
            0,
            0,
            0,
            0,
            0,
            0,
            false,
            false,
            0,
            10,
            "",
            0,
            0,
            0
        )

        dataListBanner = arrayListOf()
        adapterBanner = BannerAdapter(dataListBanner)
        imageViewPager.adapter = adapterBanner
        adapterBanner.setOnItemClickListener(object : BannerAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: BannerResponseItem, position: Int) {

            }
        })

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        catsRecyclerView.layoutManager = layoutManager
        dataListBoards = arrayListOf()
        adapterBoards = BoardAdapter(dataListBoards)
        catsRecyclerView.adapter = adapterBoards
        adapterBoards.setOnItemClickListener(object : BoardAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: BoardsData, position: Int) {

            }
        })

        val layoutManager1 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvClasses.layoutManager = layoutManager1
        dataListClasess = arrayListOf()
        adapterClasses = HomeAdapter(dataListClasess)
        rvClasses.adapter = adapterClasses
        adapterClasses.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: AllClassesResponseItem, position: Int) {
                startActivity(
                    Intent(
                        requireContext(),
                        ClassDetailsActivity::class.java
                    ).putExtra("id", obj.id).putExtra("title", obj.name)
                )

            }
        })

        val layoutManager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvTeachersList.layoutManager = layoutManager2
        dataLisTeachers = arrayListOf()
        adapterTeachers = HomeAdapter(dataLisTeachers)
        rvTeachersList.adapter = adapterTeachers
        adapterTeachers.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: AllClassesResponseItem, position: Int) {
                startActivity(
                    Intent(
                        requireContext(),
                        TeacherDetailsActivity::class.java
                    ).putExtra("id", obj.id).putExtra("title", obj.name)
                )

            }
        })

        val layoutManager3 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCourses.layoutManager = layoutManager3
        dataListCourses = arrayListOf()
        adapterCourses = CourseHomeAdapter(dataListCourses)
        rvCourses.adapter = adapterCourses
        adapterCourses.setOnItemClickListener(object : CourseHomeAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: CoursesListResponseItem, position: Int) {
                context!!.startActivity(
                    Intent(context, CourseDetailsActivity::class.java).putExtra(
                        "id",
                        obj.id
                    )
                )
            }
        })

        val layoutManager4 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvCareerExperts.layoutManager = layoutManager4
        dataListExperts = arrayListOf()
        adapterExperts = HomeAdapter(dataListExperts)
        rvCareerExperts.adapter = adapterExperts
        adapterExperts.setOnItemClickListener(object : HomeAdapter.OnItemClickListener {
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

        viewAllNewCats.setOnClickListener {
            startActivity(Intent(requireActivity(), BoardListFragment::class.java))
        }

        viewAllNewCourseTextView.setOnClickListener {
            (activity as HomeActivity).viewAll(1)
        }

        viewAllTopRatedCourseTextView.setOnClickListener {
            (activity as HomeActivity).viewAll(2)
        }

        viewAllTrendingCourseTextView.setOnClickListener {
            (activity as HomeActivity).viewAll(3)
        }

        viewAllEx.setOnClickListener {
            (activity as HomeActivity).viewAll(4)
        }

        whyChooseLink.setOnClickListener {
            startActivity(Intent(requireActivity(), LearnEarn::class.java))
        }

        llTrainingVideos.setOnClickListener {
            startActivity(
                Intent(
                    requireActivity(), TrainigVideos
                    ::class.java
                )
            )
        }
    }

    override fun showProgressbar() {
//        loader.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
//        loader.visibility = View.GONE
    }

    override fun onSuccessBanner(responseModel: Response<BannerResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.isNotEmpty()) {
            dataListBanner.clear()
            dataListBanner.addAll(responseModel.body()!!)
            adapterBanner.notifyDataSetChanged()
        }
    }

    override fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            dataListBoards.clear()
            dataListBoards.addAll(responseModel.body()!!.data)
            adapterBoards.notifyDataSetChanged()
        }
    }

    override fun onSuccess(int: Int, responseModel: Response<AllClassesResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            if (int == 1) {
                dataListClasess.clear()
                dataListClasess.addAll(responseModel.body()!!.data)
                adapterClasses.notifyDataSetChanged()
            }
            if (int == 2) {
                dataLisTeachers.clear()
                dataLisTeachers.addAll(responseModel.body()!!.data)
                adapterTeachers.notifyDataSetChanged()
            }
            if (int == 4) {
                dataListExperts.clear()
                dataListExperts.addAll(responseModel.body()!!.data)
                adapterExperts.notifyDataSetChanged()
            }
        }
    }

    override fun onSuccessCoures(responseModel: Response<CoursesListResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            dataListCourses.clear()
            dataListCourses.addAll(responseModel.body()!!.data)
            adapterCourses.notifyDataSetChanged()
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Utils.showLoginAlert(requireActivity())
            }
            500 -> {
                try {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.internal_server_error),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: IllegalStateException) {

                }
            }
            else -> {
                try {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.error),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: IllegalStateException) {

                }
            }
        }
    }

    override fun onError(throwable: Throwable) {
        try {
            Toast.makeText(requireActivity(), getString(R.string.error), Toast.LENGTH_SHORT)
                .show()
        } catch (e: IllegalStateException) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onStop()
    }
}
