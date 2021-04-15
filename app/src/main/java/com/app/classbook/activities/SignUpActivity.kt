package com.app.classbook.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.classbook.R
import com.app.classbook.SharedPreference
import com.app.classbook.adapter.BoardAdapter2
import com.app.classbook.adapter.CommonAdapter
import com.app.classbook.adapter.ImagesAdapter
import com.app.classbook.adapter.StateAdapter
import com.app.classbook.model.request.RegisterRequest
import com.app.classbook.model.request.StandardMediumBoard
import com.app.classbook.model.response.*
import com.app.classbook.presenter.RegisterPresenter
import com.app.classbook.util.Constant
import com.app.classbook.util.Utils
import com.app.classbook.util.Utils.OPERATION_CAPTURE_PHOTO
import com.app.classbook.util.Utils.OPERATION_CHOOSE_MULTIPLE_PHOTO
import com.app.classbook.util.Utils.OPERATION_CHOOSE_PHOTO
import com.app.classbook.util.Utils.capturePhoto
import com.app.classbook.util.Utils.dialogClassDate
import com.app.classbook.util.Utils.dialogStudentDOB
import com.app.classbook.util.Utils.dialogTeacherDate
import com.app.classbook.util.Utils.isValidEmail
import com.app.classbook.util.Utils.openGallery
import com.app.classbook.util.Utils.openGallery1
import com.app.classbook.util.Utils.prepareFilePart
import com.app.classbook.util.Utils.source
import com.app.classbook.view.RegisterView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.channelpartner.utils.FileUtils.getPath
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import java.io.File

class SignUpActivity : AppCompatActivity(), RegisterView.MainView, AdapterView.OnItemSelectedListener {

    private lateinit var presenter: RegisterPresenter
    var type = ""

    private lateinit var imagesAdapter: ImagesAdapter
    var files: ArrayList<Uri> = arrayListOf()
    var parts: ArrayList<MultipartBody.Part> = arrayListOf()

    private var file0: File? = null;

    var gender = ""

    var stateList: ArrayList<StateResponseItem> = arrayListOf()
    var stateID = 0

    var cityList: ArrayList<StateResponseItem> = arrayListOf()
    var cityID = 0

    var pincodeList: ArrayList<StateResponseItem> = arrayListOf()
    var pincodeID = 0

    private lateinit var boardAdapter: BoardAdapter2
    var boardList: ArrayList<BoardsData> = arrayListOf()

    private lateinit var mediumAdapter: CommonAdapter
    var mediumList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var standardAdapter: CommonAdapter
    var standardList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var subjectAdapter: CommonAdapter
    var subjectList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var subjectSpecialitiesAdapter: CommonAdapter
    var subjectSpecialitiesList: ArrayList<StateResponseItem> = arrayListOf()

    private lateinit var courseAdapter: CommonAdapter
    var courseList: ArrayList<StateResponseItem> = arrayListOf()

    private var boardid = 0
    private var mediumid = 0
    private var stdid = 0

    private var isRegister = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        init()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        presenter = RegisterPresenter(this, this)
        presenter.getStates(SharedPreference.authToken!!)
        presenter.getBoard(SharedPreference.authToken!!)
        presenter.getMedium(SharedPreference.authToken!!)
        presenter.getStandard(SharedPreference.authToken!!)
        genderLayout!!.onItemSelectedListener = this
        if (intent.extras != null) {
            type = intent.extras!!.getString("type")!!
            if (TextUtils.equals(type, "class")) {
                firstName.hint = "Class Name"
                dateOfBirth.hint = "Est. Date"
                txtSelect.text = "Select Class Logo"
                images.text = "Select Class Images"
                lastName.visibility = View.GONE
                genderLayout.visibility = View.GONE
                standard.visibility = View.GONE
                teachingExp.visibility = View.VISIBLE
                regNumber.visibility = View.VISIBLE
                rvImages.visibility = View.VISIBLE
                images.visibility = View.VISIBLE

                dateOfBirth.setOnClickListener {
                    dialogClassDate(this, dateOfBirth)
                }
            }
            if (TextUtils.equals(type, "school")) {
                firstName.hint = "School Name"
                dateOfBirth.hint = "Est. Date"
                txtSelect.text = "Select School Logo"
                images.text = "Select School Images"
                lastName.visibility = View.GONE
                genderLayout.visibility = View.GONE
                standard.visibility = View.GONE
                teachingExp.visibility = View.VISIBLE
                regNumber.visibility = View.VISIBLE
                rvImages.visibility = View.VISIBLE
                images.visibility = View.VISIBLE

                dateOfBirth.setOnClickListener {
                    dialogClassDate(this, dateOfBirth)
                }
            }
            if (TextUtils.equals(type, "student")) {
                board.visibility = View.VISIBLE
                medium.visibility = View.VISIBLE
                standard.visibility = View.VISIBLE
                teachingExp.visibility = View.GONE
                regNumber.visibility = View.GONE
                rvImages.visibility = View.GONE
                images.visibility = View.GONE

                dateOfBirth.setOnClickListener {
                    dialogStudentDOB(this, dateOfBirth)
                }
            }
            if (TextUtils.equals(type, "teacher")) {
                standard.visibility = View.GONE
                teachingExp.visibility = View.VISIBLE
                regNumber.visibility = View.GONE
                rvImages.visibility = View.GONE
                images.visibility = View.GONE

                dateOfBirth.setOnClickListener {
                    dialogTeacherDate(this, dateOfBirth)
                }
            }
            if (TextUtils.equals(type, "expert")) {
                board.visibility = View.GONE
                standard.visibility = View.GONE
                teachingExp.visibility = View.VISIBLE
                regNumber.visibility = View.GONE
                rvImages.visibility = View.GONE
                images.visibility = View.GONE

                dateOfBirth.setOnClickListener {
                    dialogTeacherDate(this, dateOfBirth)
                }
            }
        }

        helpButton.setOnClickListener {
            val intent = Intent(this, UiWebViewWebViewBasicActivity::class.java)
            intent.putExtra("title", "About Us")
            intent.putExtra("url", Constant.ABOUT_US)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

//        genderLayout.setOnItemClickListener() { parent, _, position, id ->
//            var selectedPoi = parent?.getItemAtPosition(position).toString()
//            gender = selectedPoi
//        }

        state.setOnItemClickListener() { parent, _, position, id ->
            val selectedPoi = parent.adapter.getItem(position) as StateResponseItem?
            state.setText(selectedPoi?.name)
            stateID = selectedPoi?.id!!.toInt()
            presenter.getCities(SharedPreference.authToken!!, stateID)
        }

        state.setOnTouchListener { v, event ->
            state.showDropDown()
            false
        }

        city.setOnItemClickListener() { parent, _, position, id ->
            val selectedPoi = parent.adapter.getItem(position) as StateResponseItem?
            city.setText(selectedPoi?.name)
            cityID = selectedPoi?.id!!.toInt()
            presenter.getPincodes(SharedPreference.authToken!!, cityID)
        }

        city.setOnTouchListener { v, event ->
            city.showDropDown()
            false
        }

        zipCode.setOnItemClickListener() { parent, _, position, id ->
            val selectedPoi = parent.adapter.getItem(position) as StateResponseItem?
            zipCode.setText(selectedPoi?.name)
            pincodeID = selectedPoi?.id!!.toInt()
        }

        zipCode.setOnTouchListener { v, event ->
            zipCode.showDropDown()
            false
        }

        board.setOnClickListener {
            if (boardList.size > 0)
                showBoardDialog()
        }

        medium.setOnClickListener {
            if (mediumList.size > 0)
                showMediumDialog()
        }

        standard.setOnClickListener {
            if (standardList.size > 0)
                showsStandardDialog()
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvImages!!.layoutManager = layoutManager
        imagesAdapter = ImagesAdapter(this, files!!)
        rvImages!!.adapter = imagesAdapter

        registerButton.setOnClickListener {
            submit()
        }

        txtSelect.setOnClickListener {
            showDialog()
        }

        images.setOnClickListener {
            val checkSelfPermission = ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
            ) + ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) + ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                //Requests permissions to be granted to this application at runtime
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                                android.Manifest.permission.CAMERA,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ), 2
                )
            } else {
                openGallery1(this)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        gender = parent!!.getItemAtPosition(position).toString()
    }

    private fun submit() {
        if (TextUtils.isEmpty(firstName.text.toString().trim())) {
            if (!TextUtils.equals(type, "class") && !TextUtils.equals(type, "school")) {
                firstName.error = "Enter first name"
                firstName.requestFocus()
            } else {
                firstName.error = "Enter name"
                firstName.requestFocus()
            }
            return
        }

        if (!TextUtils.equals(type, "class") && !TextUtils.equals(type, "school")) {
            if (TextUtils.isEmpty(lastName.text.toString().trim())) {
                lastName.error = "Enter last name"
                lastName.requestFocus()
                return
            }
        }
        if (!isValidEmail(email.text.toString().trim())) {
            email.error = "Enter valid email address"
            email.requestFocus()
            return
        }
        if (mobileNumber.text.toString().trim().length != 10) {
            mobileNumber.error = "Enter valid phone number"
            mobileNumber.requestFocus()
            return
        }
        if (altMobileNumber.text.toString().trim().length != 10) {
            altMobileNumber.error = "Enter valid phone number"
            altMobileNumber.requestFocus()
            return
        }
        if (TextUtils.isEmpty(dateOfBirth.text.toString().trim())) {
            if (!TextUtils.equals(type, "class") && !TextUtils.equals(type, "school")) {
                dateOfBirth.error = "Select birth date"
                dateOfBirth.requestFocus()
            } else {
                dateOfBirth.error = "Select establishment date"
                dateOfBirth.requestFocus()
            }
            return
        }
        if (!TextUtils.equals(type, "class") && !TextUtils.equals(type, "school")) {
            if (TextUtils.isEmpty(gender)) {
                Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show()
                return
            }
        }
        if (TextUtils.isEmpty(addressTextView.text.toString().trim())) {
            addressTextView.error = "Enter address"
            addressTextView.requestFocus()
            return
        }
        if (TextUtils.isEmpty(state.text.toString().trim())) {
            state.error = "Select state"
            state.requestFocus()
            return
        }
        if (TextUtils.isEmpty(city.text.toString().trim())) {
            city.error = "Select city"
            city.requestFocus()
            return
        }
        if (TextUtils.isEmpty(zipCode.text.toString().trim())) {
            zipCode.error = "Select zipCode"
            zipCode.requestFocus()
            return
        }
        if (TextUtils.equals(type, "student")) {
            if (TextUtils.isEmpty(board.text.toString().trim())) {
                board.error = "Select board"
                board.requestFocus()
                return
            }
        }
        if (TextUtils.equals(type, "student")) {
            if (TextUtils.isEmpty(medium.text.toString().trim())) {
                medium.error = "Select medium"
                medium.requestFocus()
                return
            }
        }
        if (TextUtils.equals(type, "student")) {
            if (TextUtils.isEmpty(standard.text.toString().trim())) {
                standard.error = "Select standard"
                standard.requestFocus()
                return
            }
        }
        if (!TextUtils.equals(type, "student")) {
            if (TextUtils.isEmpty(teachingExp.text.toString().trim())) {
                teachingExp.error = "Enter teaching experience"
                teachingExp.requestFocus()
                return
            }
        }
        if (TextUtils.equals(type, "class") && TextUtils.equals(type, "school")
        ) {
            if (TextUtils.isEmpty(regNumber.text.toString().trim())) {
                regNumber.error = "Enter registration number"
                regNumber.requestFocus()
                return
            }
        }

        val jsonObject = RegisterRequest(
                addressTextView.text.toString().trim(),
                altMobileNumber.text.toString().trim(),
                "",
                false,
                boardid,
                cityID,
                mobileNumber.text.toString().trim(),
                dateOfBirth.text.toString().trim(),
                "",
                email.text.toString().trim(),
                dateOfBirth.text.toString().trim(),
                firstName.text.toString().trim(),
                gender,
                lastName.text.toString().trim(),
                mediumid,
                firstName.text.toString().trim(),
                pincodeID,
                cpCode.text.toString().trim(),
                1,
                1,
                regNumber.text.toString().trim(),
                stdid,
                stateID,
                teachingExp.text.toString().trim(),
                ""
        )

        val json = Gson().toJson(jsonObject)


        var body0: MultipartBody.Part? = null
        if (file0 != null) {
            val reqFile0: RequestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file0!!)
            body0 = MultipartBody.Part.createFormData("file", file0!!.name, reqFile0)
        } else {
            val attachmentEmpty =
                    RequestBody.create(MediaType.parse("text/plain"), "")
            body0 = MultipartBody.Part.createFormData("file", "", attachmentEmpty);
        }

        if (files != null) {
            parts.clear()
            for (i in files.indices) {
                parts.add(prepareFilePart(this, "files", files.get(i))!!)
            }
        }

        val data: RequestBody =
                RequestBody.create(MultipartBody.FORM, json)

        val DeviceId: RequestBody =
                RequestBody.create(
                        MultipartBody.FORM,
                        Utils.deviceId(this)
                )
        val FCMId: RequestBody =
                RequestBody.create(
                        MultipartBody.FORM,
                        SharedPreference.token!!
                )

        isRegister = true

        if (TextUtils.equals(type, "class"))
            presenter.postClassRegisterData(body0, parts, data, DeviceId, FCMId)

        if (TextUtils.equals(type, "school"))
            presenter.postSchoolRegisterData(body0, parts, data, DeviceId, FCMId)

        if (TextUtils.equals(type, "student"))
            presenter.postStudentRegisterData(body0, data, DeviceId, FCMId)

        if (TextUtils.equals(type, "teacher"))
            presenter.postTeacherRegisterData(body0, data, DeviceId, FCMId)

        if (TextUtils.equals(type, "expert"))
            presenter.postExpertRegisterData(body0, data, DeviceId, FCMId)

    }

    override fun showProgressbar() {
        if (isRegister) {
            loader.visibility = View.VISIBLE
            bookLoading.start()
        }
    }

    override fun hideProgressbar() {
        if (isRegister) {
            isRegister = false
            loader.visibility = View.GONE
            bookLoading.stop()
        }
    }

    override fun onSuccessCommonData(int: Int, responseModel: Response<StateResponse>) {
        if (responseModel.body() != null) {
            when (int) {
                1 -> {
                    stateList.clear()
                    stateList.addAll(responseModel.body()!!)
                    val adapter =
                            StateAdapter(this, android.R.layout.simple_list_item_1, stateList!!)
                    state.setAdapter(adapter)
                }
                2 -> {
                    cityList.clear()
                    cityList.addAll(responseModel.body()!!)
                    val adapter =
                            StateAdapter(this, android.R.layout.simple_list_item_1, cityList!!)
                    city.setAdapter(adapter)
                }
                3 -> {
                    pincodeList.clear()
                    pincodeList.addAll(responseModel.body()!!)
                    val adapter =
                            StateAdapter(this, android.R.layout.simple_list_item_1, pincodeList!!)
                    zipCode.setAdapter(adapter)
                }
//                4 -> {
//                    boardList.clear()
//                    boardList.addAll(responseModel.body()!!)
//                    boardAdapter.notifyDataSetChanged()
//                }
                5 -> {
                    mediumList.clear()
                    mediumList.addAll(responseModel.body()!!)
                    mediumAdapter.notifyDataSetChanged()
                }
                6 -> {
                    standardList.clear()
                    standardList.addAll(responseModel.body()!!)
                    standardAdapter.notifyDataSetChanged()
                }
                7 -> {
                    subjectList.clear()
                    subjectList.addAll(responseModel.body()!!)
                    standardAdapter.notifyDataSetChanged()
                }
                8 -> {
                    subjectSpecialitiesList.clear()
                    subjectSpecialitiesList.addAll(responseModel.body()!!)
                    subjectSpecialitiesAdapter.notifyDataSetChanged()
                }
                9 -> {
                    courseList.clear()
                    courseList.addAll(responseModel.body()!!)
                    courseAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onSuccessBoard(int: Int, responseModel: Response<BoardsResponse>) {
        if (responseModel.body() != null && responseModel.body()!!.data.isNotEmpty()) {
            boardList.clear()
            boardList.addAll(responseModel.body()!!.data)
            boardAdapter.notifyDataSetChanged()
        }
    }

    override fun onSuccess(responseModel: Response<LoginResponse>) {
        if (responseModel.body() != null) {
            SharedPreference.isLogin = true
            SharedPreference.userId = responseModel.body()!!.data.userId
            SharedPreference.userName = responseModel.body()!!.data.email
            SharedPreference.authToken = responseModel.body()!!.data.authorizeTokenKey
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    override fun onError(errorCode: Int) {
        when (errorCode) {
            401 -> {
                Utils.showLoginAlert(this)
            }
            409 -> {
                Toast.makeText(
                        this,
                        "Email or Mobile Number is already exists",
                        Toast.LENGTH_SHORT
                )
                        .show()
            }
            500 -> {
                Toast.makeText(
                        this,
                        getString(R.string.internal_server_error),
                        Toast.LENGTH_SHORT
                )
                        .show()
            }
            else -> {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onStop()
    }

    private fun showDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_image, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val txtTakePhoto = dialogView.findViewById(R.id.txtTakePhoto) as TextView
        val txtChoosePhoto = dialogView.findViewById(R.id.txtChoosePhoto) as TextView

        txtTakePhoto.setOnClickListener {
            dialog.dismiss()
            val checkSelfPermission = ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
            ) + ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) + ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                //Requests permissions to be granted to this application at runtime
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                                android.Manifest.permission.CAMERA,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ), 1
                )
            } else {
                capturePhoto(this)
            }
        }

        txtChoosePhoto.setOnClickListener {
            dialog.dismiss()
            val checkSelfPermission = ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
            ) + ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) + ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                //Requests permissions to be granted to this application at runtime
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                                android.Manifest.permission.CAMERA,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ), 1
                )
            } else {
                openGallery(this)
            }
        }
        dialog.show()

    }

    private fun showBoardDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_recyclerview, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val rvDialog = dialogView.findViewById(R.id.rvDialog) as RecyclerView
        val btnDone = dialogView.findViewById(R.id.btnDone) as Button

        val layoutManager = LinearLayoutManager(this)
        rvDialog.layoutManager = layoutManager
        boardAdapter = BoardAdapter2(boardList)
        rvDialog.adapter = boardAdapter
        boardAdapter.setOnItemClickListener(object : BoardAdapter2.OnItemClickListener {
            override fun onItemClick(view: View, obj: BoardsData, position: Int) {
//                    for (i in standardList.indices) {
//                        if (standardList[i].isChecked) {
//                            standardList[i].isChecked = false
//                        }
//                    }
//                    obj.isChecked = !obj.isChecked
//                    boardAdapter.notifyDataSetChanged()
                boardid = obj.id
                board.setText(obj.name)
                dialog.dismiss()

            }
        })

//        btnDone.setOnClickListener {
//            val sb = StringBuffer()
//            for (i in boardList.indices) {
//                if (boardList[i].isChecked) {
//                    sb.append(boardList[i].name)
//                    sb.append(", ")
//                }
//            }
//            board.setText(sb.toString().substring(sb.toString().length - 1))
//            dialog.dismiss()
//        }
        dialog.show()
    }

    private fun showMediumDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_recyclerview, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val rvDialog = dialogView.findViewById(R.id.rvDialog) as RecyclerView
        val btnDone = dialogView.findViewById(R.id.btnDone) as Button

        val layoutManager = LinearLayoutManager(this)
        rvDialog.layoutManager = layoutManager
        mediumAdapter = CommonAdapter(mediumList)
        rvDialog.adapter = mediumAdapter
        mediumAdapter.setOnItemClickListener(object : CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: StateResponseItem, position: Int) {
//                    for (i in mediumList.indices) {
//                        if (mediumList[i].isChecked) {
//                            mediumList[i].isChecked = false
//                        }
//                    }
//                mediumAdapter.notifyDataSetChanged()
//                    obj.isChecked = !obj.isChecked
                mediumid = obj.id
                medium.setText(obj.name)
                dialog.dismiss()
            }
        })

//        btnDone.setOnClickListener {
//            val sb = StringBuffer()
//            for (i in mediumList.indices) {
//                if (mediumList[i].isChecked) {
//                    sb.append(mediumList[i].name)
//                    sb.append(", ")
//                }
//            }
//            dialog.dismiss()
//        }
        dialog.show()
    }

    private fun showsStandardDialog() {
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_recyclerview, null)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(dialogView)

        val rvDialog = dialogView.findViewById(R.id.rvDialog) as RecyclerView
        val btnDone = dialogView.findViewById(R.id.btnDone) as Button

        val layoutManager = LinearLayoutManager(this)
        rvDialog.layoutManager = layoutManager
        standardAdapter = CommonAdapter(standardList)
        rvDialog.adapter = standardAdapter
        standardAdapter.setOnItemClickListener(object : CommonAdapter.OnItemClickListener {
            override fun onItemClick(view: View, obj: StateResponseItem, position: Int) {
//                for (i in standardList.indices) {
//                    if (standardList[i].isChecked) {
//                        standardList[i].isChecked = false
//                    }
//                }
//                obj.isChecked = !obj.isChecked
//                standardAdapter.notifyDataSetChanged()
                stdid = obj.id
                standard.setText(obj.name)
                dialog.dismiss()
            }
        })

//        btnDone.setOnClickListener {
//            val sb = StringBuffer()
//            for (i in standardList.indices) {
//                if (standardList[i].isChecked) {
//
//                    sb.append(standardList[i].name)
//                    sb.append(", ")
//                }
//            }
//
//            standard.setText(sb.toString().substring(sb.toString().length - 1))
//            dialog.dismiss()
//        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            OPERATION_CAPTURE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    var requestOptions = RequestOptions()
                    requestOptions = requestOptions.format(DecodeFormat.PREFER_ARGB_8888)
                    requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)

                    if (source != null) {
                        if (source!!.exists()) {
                            val uri = Uri.fromFile(source)
                            try {
                                files.add(uri)
                                imagesAdapter.notifyDataSetChanged()
                            } catch (e: java.lang.Exception) {
                                Log.e("TAG", "File select error", e)
                            }
                        }
                    }

                }
            OPERATION_CHOOSE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    var requestOptions = RequestOptions()
                    requestOptions = requestOptions.format(DecodeFormat.PREFER_ARGB_8888)
                    requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
                    if (Build.VERSION.SDK_INT >= 19) {
                        val uri: Uri = data!!.data!!
                        try {
                            val path = getPath(this, uri)!!
                            val file = File(path)
                            file0 = file
                            Glide.with(this).load(uri)
                                    .apply(requestOptions).into(imageView98)
                        } catch (e: java.lang.Exception) {
                            Log.e("TAG", "File select error", e)
                        }
                    }
                }

            OPERATION_CHOOSE_MULTIPLE_PHOTO ->
                if (resultCode == Activity.RESULT_OK) {
                    files.clear()
                    if (data!!.clipData != null) {
                        val count: Int = data!!.clipData!!.itemCount
                        var currentItem = 0
                        while (currentItem < count) {
                            val imageUri: Uri =
                                    data!!.clipData!!.getItemAt(currentItem).uri
                            currentItem += 1
                            Log.d("Uri Selected", imageUri.toString())
                            try {
                                files.add(imageUri)
                                imagesAdapter.notifyDataSetChanged()
                            } catch (e: Exception) {
                                Log.e("TAG", "File select error", e)
                            }
                        }
                    } else if (data.data != null) {
                        val uri: Uri = data.data!!
                        try {
                            files.add(uri)
                            imagesAdapter.notifyDataSetChanged()
                        } catch (e: java.lang.Exception) {
                            Log.e("TAG", "File select error", e)
                        }
                    }
                }
        }
    }


    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<out String>, grantedResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantedResults)
        when (requestCode) {
            1 ->
                if (grantedResults.isNotEmpty() && grantedResults[0] ==
                        PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery(this)
                } else {
                    //show("Unfortunately You are Denied Permission to Perform this Operataion.")
                }
            2 ->
                if (grantedResults.isNotEmpty() && grantedResults[0] ==
                        PackageManager.PERMISSION_GRANTED
                ) {
                    openGallery1(this)
                } else {
                    //show("Unfortunately You are Denied Permission to Perform this Operataion.")
                }
        }
    }
}
