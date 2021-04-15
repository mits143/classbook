package com.app.classbook.network

import com.app.classbook.model.request.ListingRequest
import com.app.classbook.model.response.*
import com.app.classbook.util.Constant.Companion.ADD_TO_CART
import com.app.classbook.util.Constant.Companion.ADD_TO_FAV
import com.app.classbook.util.Constant.Companion.ADVERTISMENT_BANNER
import com.app.classbook.util.Constant.Companion.All_CLASSES
import com.app.classbook.util.Constant.Companion.All_COURSES
import com.app.classbook.util.Constant.Companion.All_EXPERTS
import com.app.classbook.util.Constant.Companion.All_TEACHER
import com.app.classbook.util.Constant.Companion.BOARD
import com.app.classbook.util.Constant.Companion.CART_LIST
import com.app.classbook.util.Constant.Companion.CHANGE_PWD
import com.app.classbook.util.Constant.Companion.CITY
import com.app.classbook.util.Constant.Companion.CLASS_DETAIL
import com.app.classbook.util.Constant.Companion.COURSES
import com.app.classbook.util.Constant.Companion.COURSES_DETAIL
import com.app.classbook.util.Constant.Companion.COURSES_SUBTOPICS
import com.app.classbook.util.Constant.Companion.COURSES_SUBTOPICSVIDEO
import com.app.classbook.util.Constant.Companion.ClASS_REGISTER
import com.app.classbook.util.Constant.Companion.EDITSTUDENT
import com.app.classbook.util.Constant.Companion.EXPERT_CAREER_DETAIL
import com.app.classbook.util.Constant.Companion.EXPERT_REGISTER
import com.app.classbook.util.Constant.Companion.FORGOT_PWD
import com.app.classbook.util.Constant.Companion.GETBOARD
import com.app.classbook.util.Constant.Companion.GETSTANDARDMEDIUMBOARDMAPPING
import com.app.classbook.util.Constant.Companion.GETSTUDENTBYID
import com.app.classbook.util.Constant.Companion.GET_FAVOURITE
import com.app.classbook.util.Constant.Companion.GET_SUBSCRIPTION_DETAIL
import com.app.classbook.util.Constant.Companion.GET_TRANSACTION_DETAIL
import com.app.classbook.util.Constant.Companion.HOWTOEARNDETAIL
import com.app.classbook.util.Constant.Companion.HOWTOEARNLIST
import com.app.classbook.util.Constant.Companion.LEARNANDEARNREGISTRATIONPROCESSDETAIL
import com.app.classbook.util.Constant.Companion.LEARNANDEARNREGISTRATIONPROCESSLIST
import com.app.classbook.util.Constant.Companion.LOGIN
import com.app.classbook.util.Constant.Companion.MEDIUM
import com.app.classbook.util.Constant.Companion.PERCENTAGEEARNINGS
import com.app.classbook.util.Constant.Companion.PINCODE
import com.app.classbook.util.Constant.Companion.REMOVE_FROM_CART
import com.app.classbook.util.Constant.Companion.SCHOOL_REGISTER
import com.app.classbook.util.Constant.Companion.STANDARD
import com.app.classbook.util.Constant.Companion.STATES
import com.app.classbook.util.Constant.Companion.STUDENT_REGISTER
import com.app.classbook.util.Constant.Companion.SUBJECT
import com.app.classbook.util.Constant.Companion.SUBJECTLIST
import com.app.classbook.util.Constant.Companion.SUBJECT_SPECIALITIES
import com.app.classbook.util.Constant.Companion.SUB_TOPIC_DATA
import com.app.classbook.util.Constant.Companion.SUB_VIDEO_DATA
import com.app.classbook.util.Constant.Companion.TEACHER_DETAIL
import com.app.classbook.util.Constant.Companion.TEACHER_REGISTER
import com.app.classbook.util.Constant.Companion.TOPIC_DATA
import com.app.classbook.util.Constant.Companion.TRAININGVIDEODETAIL
import com.app.classbook.util.Constant.Companion.TRAININGVIDEOLIST
import com.google.gson.JsonObject
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface GetDataServices {

    @FormUrlEncoded
    @POST(LOGIN)
    fun postLogin(
            @Header("AuthorizeTokenKey") token: String,
            @Field(
                    value = "Email",
                    encoded = false
            ) Email: String,
            @Field(
                    value = "Password",
                    encoded = false
            ) Password: String,
            @Field(
                    value = "DeviceId",
                    encoded = false
            ) DeviceId: String,
            @Field(
                    value = "FCMId",
                    encoded = false
            ) FCMId: String
    ): Observable<Response<LoginResponse>>

    @Multipart
    @POST(ClASS_REGISTER)
    fun postClassRegister(
            @Part file: MultipartBody.Part,
            @Part files: ArrayList<MultipartBody.Part>,
            @Part("data") data: RequestBody,
            @Part("DeviceId") DeviceId: RequestBody,
            @Part("FCMId") FCMId: RequestBody
    ): Observable<Response<LoginResponse>>

    @Multipart
    @POST(SCHOOL_REGISTER)
    fun postSchoolRegister(
            @Part file: MultipartBody.Part,
            @Part files: ArrayList<MultipartBody.Part>,
            @Part("data") data: RequestBody,
            @Part("DeviceId") DeviceId: RequestBody,
            @Part("FCMId") FCMId: RequestBody
    ): Observable<Response<LoginResponse>>

    @Multipart
    @POST(STUDENT_REGISTER)
    fun postStudentRegister(
            @Part file: MultipartBody.Part,
            @Part("data") data: RequestBody,
            @Part("DeviceId") DeviceId: RequestBody,
            @Part("FCMId") FCMId: RequestBody
    ): Observable<Response<LoginResponse>>

    @Multipart
    @POST(TEACHER_REGISTER)
    fun postTeacherRegister(
            @Part file: MultipartBody.Part,
            @Part("data") data: RequestBody,
            @Part("DeviceId") DeviceId: RequestBody,
            @Part("FCMId") FCMId: RequestBody
    ): Observable<Response<LoginResponse>>

    @Multipart
    @POST(EXPERT_REGISTER)
    fun postExpertRegister(
            @Part file: MultipartBody.Part,
            @Part("data") data: RequestBody,
            @Part("DeviceId") DeviceId: RequestBody,
            @Part("FCMId") FCMId: RequestBody
    ): Observable<Response<LoginResponse>>

    @FormUrlEncoded
    @POST(FORGOT_PWD)
    fun postForgotPwd(
            @Field(
                    value = "Email",
                    encoded = false
            ) Email: String
    ): Observable<Response<JsonObject>>

    @FormUrlEncoded
    @POST(CHANGE_PWD)
    fun postChangePwd(
            @Header("AuthorizeTokenKey") token: String,
            @Field(
                    value = "OldPassword",
                    encoded = false
            ) oldPwd: String,
            @Field(
                    value = "NewPassword",
                    encoded = false
            ) newPwd: String
    ): Observable<Response<JsonObject>>

    @GET(STATES)
    fun getStates(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<StateResponse>>

    @GET(CITY)
    fun getCities(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<StateResponse>>

    @GET(PINCODE)
    fun getPincodes(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<StateResponse>>

    @GET(BOARD)
    fun getBoards(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<StateResponse>>

    @GET(MEDIUM)
    fun getMediums(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<StateResponse>>

    @GET(STANDARD)
    fun getStandards(@Header("AuthorizeTokenKey") token: String): Observable<Response<StateResponse>>

    @GET(SUBJECT)
    fun getSubjects(): Observable<Response<StateResponse>>

    @GET(SUBJECT_SPECIALITIES)
    fun getSubjectSpecialities(): Observable<Response<StateResponse>>

    @GET(COURSES)
    fun getCourses(@Header("AuthorizeTokenKey") token: String): Observable<Response<StateResponse>>

    @POST(All_CLASSES)
    fun getAllClasses(
            @Header("AuthorizeTokenKey") token: String,
            @Body jsonObject: ListingRequest
    ): Observable<Response<AllClassesResponse>>

    @GET(CLASS_DETAIL)
    fun getClassDetail(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<ClassDetailResponse>>

    @POST(All_TEACHER)
    fun getAllTeachers(
            @Header("AuthorizeTokenKey") token: String,
            @Body jsonObject: ListingRequest
    ): Observable<Response<AllClassesResponse>>

    @GET(TEACHER_DETAIL)
    fun getTeacherDetail(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<ClassDetailResponse>>

    @POST(All_COURSES)
    fun getAllCourses(
            @Header("AuthorizeTokenKey") token: String,
            @Body jsonObject: ListingRequest
    ): Observable<Response<CoursesListResponse>>

    @POST(All_EXPERTS)
    fun getAllCareerExpert(
            @Header("AuthorizeTokenKey") token: String,
            @Body jsonObject: ListingRequest
    ): Observable<Response<AllClassesResponse>>

    @GET(EXPERT_CAREER_DETAIL)
    fun getExpertCareerDetail(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<ExpertCareerDetailResponse>>

    @GET(SUBJECTLIST)
    fun postSubjectsByModuleId(
            @Header("AuthorizeTokenKey") token: String,
            @Path("smbId") smbId: Int
    ): Observable<Response<SubjectResponse>>

    @FormUrlEncoded
    @POST(ADD_TO_CART)
    fun postAddToCart(
            @Header("AuthorizeTokenKey") token: String,
            @Field(
                    value = "MappingId",
                    encoded = false
            ) MappingId: Int,
            @Field(
                    value = "TypeOfMapping",
                    encoded = false
            ) TypeOfMapping: String,
            @Field(
                    value = "Type",
                    encoded = false
            ) Type: String
    ): Observable<Response<JsonObject>>

    @GET(ADVERTISMENT_BANNER)
    fun getBanner(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<BannerResponse>>

    @FormUrlEncoded
    @POST(ADD_TO_FAV)
    fun postAddToFav(
            @Header("AuthorizeTokenKey") token: String,
            @Field(
                    value = "EntityId",
                    encoded = false
            ) EntityId: Int,
            @Field(
                    value = "EntityName",
                    encoded = false
            ) EntityName: String
    ): Observable<Response<JsonObject>>

    @POST(CART_LIST)
    fun getCartList(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<CartListResponse>>

    @GET(TOPIC_DATA)
    fun getTopicData(
            @Header("AuthorizeTokenKey") token: String,
            @Query("smbid") smbid: Int,
            @Query("subjectId") subjectId: Int
    ): Observable<Response<SubjectTopicResponse>>

    @GET(SUB_TOPIC_DATA)
    fun getSubTopicData(
            @Header("AuthorizeTokenKey") token: String,
            @Query("smbId") smbId: Int,
            @Query("subjectId") subjectId: Int,
            @Query("topicId") topicId: Int
    ): Observable<Response<SubTopicResponse>>

    @GET(SUB_VIDEO_DATA)
    fun getSubVideoData(
            @Header("AuthorizeTokenKey") token: String,
            @Query("smbid") smbId: Int,
            @Query("subjectId") subjectId: Int,
            @Query("topicId") topicId: Int,
            @Query("subtopicId") subTopicId: Int
    ): Observable<Response<SubjectVideoResponse>>

    @POST(GET_SUBSCRIPTION_DETAIL)
    fun getSubscriptionDetail(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<SubscriptionResponse>>

    @POST(GET_TRANSACTION_DETAIL)
    fun getTransactionDetail(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<TransactionResponse>>

    @POST(REMOVE_FROM_CART)
    fun postRemoveFromCart(
            @Header("AuthorizeTokenKey") token: String,
            @Path("cartItemId") cartItemId: Int
    ): Observable<Response<JsonObject>>

    @GET(GET_FAVOURITE)
    fun getfavourite(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<FavoriteResponse>>

    @GET(GETSTUDENTBYID)
    fun getstudentbyid(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<ProfileResponse>>

    @POST(EDITSTUDENT)
    fun editstudent(
            @Header("AuthorizeTokenKey") token: String,
            @Part file: MultipartBody.Part,
            @Part("data") data: RequestBody
    ): Observable<Response<JsonObject>>

    @GET(GETSTANDARDMEDIUMBOARDMAPPING)
    fun getStandardMediumBoardMapping(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<SMBResponse>>

    @GET(COURSES_DETAIL)
    fun getCourses_Detail(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<CourseDetailResponse>>

    @GET(COURSES_SUBTOPICS)
    fun getcourses_subtopics(
            @Header("AuthorizeTokenKey") token: String,
            @Path("topicId") topicId: Int,
            @Path("courseId") courseId: Int
    ): Observable<Response<CourseSubTopicResponse>>

    @GET(COURSES_SUBTOPICSVIDEO)
    fun getcourses_subtopicsvideo(
            @Header("AuthorizeTokenKey") token: String,
            @Path("topicId") topicId: Int,
            @Path("courseId") courseId: Int,
            @Path("subtopicId") subtopicId: Int
    ): Observable<Response<CourseVideoResponse>>

    @GET(GETBOARD)
    fun getBoard(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<BoardsResponse>>

    @GET(LEARNANDEARNREGISTRATIONPROCESSLIST)
    fun learnandearnregistrationprocesslist(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<LearnEarnResponse>>

    @GET(LEARNANDEARNREGISTRATIONPROCESSDETAIL)
    fun learnandearnregistrationprocessdetail(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<BoardsResponse>>

    @GET(HOWTOEARNLIST)
    fun howtoearnlist(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<LearnEarnResponse>>

    @GET(HOWTOEARNDETAIL)
    fun howtoearndetail(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<BoardsResponse>>

    @GET(TRAININGVIDEOLIST)
    fun trainingvideolist(
            @Header("AuthorizeTokenKey") token: String,
    ): Observable<Response<TrainingVideoResponse>>

    @GET(TRAININGVIDEODETAIL)
    fun trainingvideodetail(
            @Header("AuthorizeTokenKey") token: String,
            @Path("id") id: Int
    ): Observable<Response<BoardsResponse>>

    @GET(PERCENTAGEEARNINGS)
    fun percentageearnings(
            @Header("AuthorizeTokenKey") token: String
    ): Observable<Response<LevelResponse>>
}