package com.app.classbook.presenter


import android.content.Context
import android.widget.Toast
import com.app.classbook.R
import com.app.classbook.network.ApiClient
import com.app.classbook.util.NetWorkConection
import com.app.classbook.view.FragmentHomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class FragmentHomePresenter : FragmentHomeView.MainPresenter {

    var context: Context? = null
    var mainView: FragmentHomeView.MainView? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(context: Context?, mainView: FragmentHomeView.MainView?) {
        this.context = context
        this.mainView = mainView
    }

    override fun loadDataBanner(token: String) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                .getBanner(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mainView!!.hideProgressbar()
                    when (val responseCode = listResponse.code()) {
                        200, 201, 202, 204 -> {
                            mainView!!.onSuccessBanner(listResponse)
                        }
                        400, 401, 500 -> {
                            mainView!!.onError(responseCode)
                        }
                    }
                }, { error ->
                    mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response!!.code()) {
                            //Responce Code
                        }
                    } else {
                        //Handle Other Errors
                    }
                    mainView!!.onError(error)
                })
        } else {
            mainView!!.hideProgressbar()
            Toast.makeText(
                context!!,
                context!!.getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun loadDataBoard(token: String) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                .getBoard(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mainView!!.hideProgressbar()
                    when (val responseCode = listResponse.code()) {
                        200, 201, 202, 204 -> {
                            mainView!!.onSuccessBoard(1, listResponse)
                        }
                        400, 401, 500 -> {
                            mainView!!.onError(responseCode)
                        }
                    }
                }, { error ->
                    mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response!!.code()) {
                            //Responce Code
                        }
                    } else {
                        //Handle Other Errors
                    }
                    mainView!!.onError(error)
                })
        } else {
            mainView!!.hideProgressbar()
            Toast.makeText(
                context!!,
                context!!.getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun loadDataClasses(
        token: String,
        boardId: Int,
        cityId: Int,
        classId: Int,
        courseCategoryId: Int,
        expertiesId: Int,
        mediumId: Int,
        onlineLive: Boolean,
        onlineLivePhysical: Boolean,
        pageIndex: Int,
        pageSize: Int,
        searchName: String,
        standardId: Int,
        stateId: Int,
        teacherId: Int
    ) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                .getAllClasses(
                    token,
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mainView!!.hideProgressbar()
                    when (val responseCode = listResponse.code()) {
                        200, 201, 202, 204 -> {
                            mainView!!.onSuccess(1, listResponse)
                        }
                        400, 401, 500 -> {
                            mainView!!.onError(responseCode)
                        }
                    }
                }, { error ->
                    mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response!!.code()) {
                            //Responce Code
                        }
                    } else {
                        //Handle Other Errors
                    }
                    mainView!!.onError(error)
                })
        } else {
            mainView!!.hideProgressbar()
            Toast.makeText(
                context!!,
                context!!.getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun loadDataTeacher(
        token: String,
        boardId: Int,
        cityId: Int,
        classId: Int,
        courseCategoryId: Int,
        expertiesId: Int,
        mediumId: Int,
        onlineLive: Boolean,
        onlineLivePhysical: Boolean,
        pageIndex: Int,
        pageSize: Int,
        searchName: String,
        standardId: Int,
        stateId: Int,
        teacherId: Int
    ) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                .getAllTeachers(
                    token,
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mainView!!.hideProgressbar()
                    when (val responseCode = listResponse.code()) {
                        200, 201, 202, 204 -> {
                            mainView!!.onSuccess(2, listResponse)
                        }
                        400, 401, 500 -> {
                            mainView!!.onError(responseCode)
                        }
                    }
                }, { error ->
                    mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response!!.code()) {
                            //Responce Code
                        }
                    } else {
                        //Handle Other Errors
                    }
                    mainView!!.onError(error)
                })
        } else {
            mainView!!.hideProgressbar()
            Toast.makeText(
                context!!,
                context!!.getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun loadDataCourses(
        token: String,
        boardId: Int,
        cityId: Int,
        classId: Int,
        courseCategoryId: Int,
        expertiesId: Int,
        mediumId: Int,
        onlineLive: Boolean,
        onlineLivePhysical: Boolean,
        pageIndex: Int,
        pageSize: Int,
        searchName: String,
        standardId: Int,
        stateId: Int,
        teacherId: Int
    ) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                .getAllCourses(
                    token,
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mainView!!.hideProgressbar()
                    when (val responseCode = listResponse.code()) {
                        200, 201, 202, 204 -> {
                            mainView!!.onSuccessCoures(listResponse)
                        }
                        400, 401, 500 -> {
                            mainView!!.onError(responseCode)
                        }
                    }
                }, { error ->
                    mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response!!.code()) {
                            //Responce Code
                        }
                    } else {
                        //Handle Other Errors
                    }
                    mainView!!.onError(error)
                })
        } else {
            mainView!!.hideProgressbar()
            Toast.makeText(
                context!!,
                context!!.getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun loadDataExperts(
        token: String,
        boardId: Int,
        cityId: Int,
        classId: Int,
        courseCategoryId: Int,
        expertiesId: Int,
        mediumId: Int,
        onlineLive: Boolean,
        onlineLivePhysical: Boolean,
        pageIndex: Int,
        pageSize: Int,
        searchName: String,
        standardId: Int,
        stateId: Int,
        teacherId: Int
    ) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                .getAllCareerExpert(
                    token,
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mainView!!.hideProgressbar()
                    when (val responseCode = listResponse.code()) {
                        200, 201, 202, 204 -> {
                            mainView!!.onSuccess(4, listResponse)
                        }
                        400, 401, 500 -> {
                            mainView!!.onError(responseCode)
                        }
                    }
                }, { error ->
                    mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = (error as HttpException).response()
                        when (response!!.code()) {
                            //Responce Code
                        }
                    } else {
                        //Handle Other Errors
                    }
                    mainView!!.onError(error)
                })
        } else {
            mainView!!.hideProgressbar()
            Toast.makeText(
                context!!,
                context!!.getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show();
        }
    }

    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}