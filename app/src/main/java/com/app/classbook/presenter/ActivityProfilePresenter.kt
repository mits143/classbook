package com.app.classbook.presenter


import android.content.Context
import android.widget.Toast
import com.app.classbook.R
import com.app.classbook.network.ApiClient
import com.app.classbook.util.NetWorkConection
import com.app.classbook.view.ActivityProfileView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.http.Header


class ActivityProfilePresenter : ActivityProfileView.MainPresenter {

    var context: Context? = null
    var mainView: ActivityProfileView.MainView? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(context: Context?, mainView: ActivityProfileView.MainView?) {
        this.context = context
        this.mainView = mainView
    }

    override fun getStates(
            token: String
    ) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                    .getStates(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ listResponse ->
                        mainView!!.hideProgressbar()
                        when (val responseCode = listResponse.code()) {
                            200, 201, 202, 204 -> {
                                mainView!!.onSuccessCommonData(1, listResponse)
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

    override fun getCities(
            token: String,
            id: Int
    ) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                    .getCities(token, id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ listResponse ->
                        mainView!!.hideProgressbar()
                        when (val responseCode = listResponse.code()) {
                            200, 201, 202, 204 -> {
                                mainView!!.onSuccessCommonData(2, listResponse)
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

    override fun getBoard(token: String) {
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

    override fun getMedium(token: String) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                    .getMediums(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ listResponse ->
                        mainView!!.hideProgressbar()
                        when (val responseCode = listResponse.code()) {
                            200, 201, 202, 204 -> {
                                mainView!!.onSuccessCommonData(5, listResponse)
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

    override fun getStandard(token: String) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                    .getStandards(token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ listResponse ->
                        mainView!!.hideProgressbar()
                        when (val responseCode = listResponse.code()) {
                            200, 201, 202, 204 -> {
                                mainView!!.onSuccessCommonData(6, listResponse)
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

    override fun loadData(
        token: String
    ) {
        mainView!!.showProgressbar()

        if (NetWorkConection.isNEtworkConnected(context!!)) {
            disposable = ApiClient.instance
                .getstudentbyid(
                    token
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