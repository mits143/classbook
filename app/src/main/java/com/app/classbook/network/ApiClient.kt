package com.app.classbook.network

import com.app.classbook.model.request.ListingRequest
import com.app.classbook.model.request.LoginRequest
import com.app.classbook.model.response.*
import com.app.classbook.util.Constant.Companion.BASE_URL
import com.app.classbook.util.Constant.Companion.SECRET_KEY
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ApiClient {

    private val myAppService: GetDataServices

    companion object {
        private var apiClient: ApiClient? = null
        val instance: ApiClient
            get() {
                if (apiClient == null) {
                    apiClient =
                        ApiClient()
                }
                return apiClient as ApiClient
            }
    }

    init {
        val okHttpClient: OkHttpClient = UnsafeOkHttpClient.unsafeOkHttpClient
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        myAppService = retrofit.create(GetDataServices::class.java)
    }

    object UnsafeOkHttpClient {
        val unsafeOkHttpClient: OkHttpClient
            get() = try {
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                val sslSocketFactory = sslContext.socketFactory
                val builder = OkHttpClient.Builder()
                builder.connectTimeout(30, TimeUnit.SECONDS)
                builder.readTimeout(30, TimeUnit.SECONDS)
                builder.writeTimeout(30, TimeUnit.SECONDS)
                builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                builder.hostnameVerifier { hostname, session -> true }
                builder.addInterceptor { chain ->
                    val original = chain.request()
                    val request = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Secret_Key", SECRET_KEY)
                        .method(original.method(), original.body())
                        .build()
                    chain.proceed(request)
                }
                builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
    }

    fun postLogin(
        token: String,
        Email: String,
        Password: String,
        DeviceId: String,
        FCMId: String
    ): Observable<Response<LoginResponse>> {
        return myAppService.postLogin(
            token,
            Email,
            Password,
            DeviceId,
            FCMId
        )
    }

    fun postClassRegister(
        file: MultipartBody.Part,
        files: ArrayList<MultipartBody.Part>,
        data: RequestBody,
        DeviceId: RequestBody,
        FCMId: RequestBody
    ): Observable<Response<LoginResponse>> {
        return myAppService.postClassRegister(
            file,
            files,
            data,
            DeviceId,
            FCMId
        )
    }

    fun postSchoolRegister(
        file: MultipartBody.Part,
        files: ArrayList<MultipartBody.Part>,
        data: RequestBody,
        DeviceId: RequestBody,
        FCMId: RequestBody
    ): Observable<Response<LoginResponse>> {
        return myAppService.postSchoolRegister(
            file,
            files,
            data,
            DeviceId,
            FCMId
        )
    }

    fun postStudentRegister(
        file: MultipartBody.Part,
        data: RequestBody,
        DeviceId: RequestBody,
        FCMId: RequestBody
    ): Observable<Response<LoginResponse>> {
        return myAppService.postStudentRegister(
            file,
            data,
            DeviceId,
            FCMId
        )
    }

    fun postTeacherRegister(
        file: MultipartBody.Part,
        data: RequestBody,
        DeviceId: RequestBody,
        FCMId: RequestBody
    ): Observable<Response<LoginResponse>> {
        return myAppService.postTeacherRegister(
            file,
            data,
            DeviceId,
            FCMId
        )
    }

    fun postExpertRegister(
        file: MultipartBody.Part,
        data: RequestBody,
        DeviceId: RequestBody,
        FCMId: RequestBody
    ): Observable<Response<LoginResponse>> {
        return myAppService.postExpertRegister(
            file,
            data,
            DeviceId,
            FCMId
        )
    }

    fun postFogotPwd(
        Email: String
    ): Observable<Response<JsonObject>> {
        return myAppService.postForgotPwd(
            Email
        )
    }

    fun postChangePwd(
        token: String,
        oldPwd: String,
        newPwd: String
    ): Observable<Response<JsonObject>> {
        return myAppService.postChangePwd(
            token,
            oldPwd,
            newPwd
        )
    }

    fun getStates(
        token: String
    ): Observable<Response<StateResponse>> {
        return myAppService.getStates(token)
    }

    fun getCities(
        token: String,
        id: Int
    ): Observable<Response<StateResponse>> {
        return myAppService.getCities(token, id)
    }

    fun getPincodes(
        token: String,
        id: Int
    ): Observable<Response<StateResponse>> {
        return myAppService.getPincodes(token, id)
    }

    fun getBoards(
        token: String
    ): Observable<Response<StateResponse>> {
        return myAppService.getBoards(token)
    }

    fun getMediums(
        token: String
    ): Observable<Response<StateResponse>> {
        return myAppService.getMediums(token)
    }

    fun getStandards(
        token: String
    ): Observable<Response<StateResponse>> {
        return myAppService.getStandards(token)
    }

    fun getSubjects(): Observable<Response<StateResponse>> {
        return myAppService.getSubjects()
    }

    fun getSubjectSpecialities(): Observable<Response<StateResponse>> {
        return myAppService.getSubjectSpecialities()
    }

    fun getCourses(
        token: String
    ): Observable<Response<StateResponse>> {
        return myAppService.getCourses(token)
    }

    fun getAllClasses(
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
    ): Observable<Response<AllClassesResponse>> {
        val jsonObject = ListingRequest(
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
        return myAppService.getAllClasses(token, jsonObject)
    }

    fun getClassDetail(
        token: String,
        id: Int
    ): Observable<Response<ClassDetailResponse>> {
        return myAppService.getClassDetail(
            token,
            id
        )
    }

    fun getAllTeachers(
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
    ): Observable<Response<AllClassesResponse>> {
        val jsonObject = ListingRequest(
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
        return myAppService.getAllTeachers(token, jsonObject)
    }

    fun getTeacherDetail(
        token: String,
        id: Int
    ): Observable<Response<ClassDetailResponse>> {
        return myAppService.getTeacherDetail(
            token,
            id
        )
    }

    fun getAllCourses(
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
    ): Observable<Response<CoursesListResponse>> {
        val jsonObject = ListingRequest(
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
        return myAppService.getAllCourses(token, jsonObject)
    }

    fun getAllCareerExpert(
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
    ): Observable<Response<AllClassesResponse>> {
        val jsonObject = ListingRequest(
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
        return myAppService.getAllCareerExpert(token, jsonObject)
    }

    fun getExpertCareerDetail(
        token: String,
        id: Int
    ): Observable<Response<ExpertCareerDetailResponse>> {
        return myAppService.getExpertCareerDetail(
            token,
            id
        )
    }

    fun postSubjectsByModuleId(
        token: String,
        Id: Int
    ): Observable<Response<SubjectResponse>> {
        return myAppService.postSubjectsByModuleId(
            token,
            Id
        )
    }

    fun postAddToCart(
        token: String,
        MappingId: Int,
        TypeOfMapping: String,
        Type: String
    ): Observable<Response<JsonObject>> {
        return myAppService.postAddToCart(
            token,
            MappingId,
            TypeOfMapping,
            Type
        )
    }

    fun getBanner(
        token: String
    ): Observable<Response<BannerResponse>> {
        return myAppService.getBanner(
            token
        )
    }

    fun postAddToFav(
        token: String,
        EntityId: Int,
        EntityName: String
    ): Observable<Response<JsonObject>> {
        return myAppService.postAddToFav(
            token,
            EntityId,
            EntityName
        )
    }

    fun getCartList(
        token: String
    ): Observable<Response<CartListResponse>> {
        return myAppService.getCartList(
            token
        )
    }

    fun getTopicData(
        token: String,
        smbid: Int,
        subjectId: Int
    ): Observable<Response<SubjectTopicResponse>> {
        return myAppService.getTopicData(
            token,
            smbid,
            subjectId
        )
    }

    fun getSubTopicData(
        token: String,
        smbId: Int,
        subjectId: Int,
        topicId: Int
    ): Observable<Response<SubTopicResponse>> {
        return myAppService.getSubTopicData(
            token,
            smbId,
            subjectId,
            topicId
        )
    }

    fun getSubVideoData(
        token: String,
        topicId: Int,
        smbId: Int,
        subjectId: Int,
        subTopicId: Int
    ): Observable<Response<SubjectVideoResponse>> {
        return myAppService.getSubVideoData(
            token,
            topicId,
            smbId,
            subjectId,
            subTopicId
        )
    }

    fun getSubscriptionDetail(
        token: String
    ): Observable<Response<SubscriptionResponse>> {
        return myAppService.getSubscriptionDetail(
            token
        )
    }

    fun getTransactionDetail(
        token: String
    ): Observable<Response<TransactionResponse>> {
        return myAppService.getTransactionDetail(
            token
        )
    }

    fun postRemoveFromCart(
        token: String,
        cartItemId: Int
    ): Observable<Response<JsonObject>> {
        return myAppService.postRemoveFromCart(
            token,
            cartItemId
        )
    }

    fun getfavourite(
        token: String
    ): Observable<Response<FavoriteResponse>> {
        return myAppService.getfavourite(
            token
        )
    }

    fun getstudentbyid(
        token: String
    ): Observable<Response<ProfileResponse>> {
        return myAppService.getstudentbyid(
            token
        )
    }

    fun editstudent(
        token: String,
        file: MultipartBody.Part,
        data: RequestBody
    ): Observable<Response<JsonObject>> {
        return myAppService.editstudent(
            token,
            file,
            data,
        )
    }

    fun getStandardMediumBoardMapping(
        token: String,
        id: Int
    ): Observable<Response<SMBResponse>> {
        return myAppService.getStandardMediumBoardMapping(
            token,
            id
        )
    }

    fun getCourses_Detail(
        token: String,
        id: Int
    ): Observable<Response<CourseDetailResponse>> {
        return myAppService.getCourses_Detail(
            token,
            id
        )
    }

    fun getcourses_subtopics(
        token: String,
        topicId: Int,
        courseId: Int
    ): Observable<Response<CourseSubTopicResponse>> {
        return myAppService.getcourses_subtopics(
            token,
            topicId,
            courseId
        )
    }

    fun getcourses_subtopicsvideo(
        token: String,
        topicId: Int,
        courseId: Int,
        subtopicId: Int,
    ): Observable<Response<CourseVideoResponse>> {
        return myAppService.getcourses_subtopicsvideo(
            token,
            topicId,
            courseId,
            subtopicId,
        )
    }

    fun getBoard(
        token: String
    ): Observable<Response<BoardsResponse>> {
        return myAppService.getBoard(
            token
        )
    }

    fun learnandearnregistrationprocesslist(
        token: String
    ): Observable<Response<LearnEarnResponse>> {
        return myAppService.learnandearnregistrationprocesslist(
            token
        )
    }

    fun learnandearnregistrationprocessdetail(
        token: String,
        id: Int
    ): Observable<Response<BoardsResponse>> {
        return myAppService.learnandearnregistrationprocessdetail(
            token,
            id
        )
    }

    fun howtoearnlist(
        token: String
    ): Observable<Response<LearnEarnResponse>> {
        return myAppService.howtoearnlist(
            token
        )
    }

    fun howtoearndetail(
        token: String,
        id: Int
    ): Observable<Response<BoardsResponse>> {
        return myAppService.howtoearndetail(
            token,
            id
        )
    }

    fun trainingvideolist(
        token: String
    ): Observable<Response<TrainingVideoResponse>> {
        return myAppService.trainingvideolist(
            token
        )
    }

    fun percentageearnings(
        token: String
    ): Observable<Response<LevelResponse>> {
        return myAppService.percentageearnings(
            token
        )
    }
}