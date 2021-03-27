package com.app.classbook.presenter


import android.content.Context
import android.widget.Toast
import com.app.classbook.R
import com.app.classbook.network.ApiClient
import com.app.classbook.util.NetWorkConection
import com.app.classbook.view.FragmentExpertsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.http.Header

class FragmentExpertsPresenter : FragmentExpertsView.MainPresenter {

    var context: Context? = null
    var mainView: FragmentExpertsView.MainView? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(context: Context?, mainView: FragmentExpertsView.MainView?) {
        this.context = context
        this.mainView = mainView
    }

    override fun loadData(
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
                            mainView!!.onSuccess(listResponse)
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