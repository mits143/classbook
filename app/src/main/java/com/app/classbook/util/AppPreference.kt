package com.app.classbook.util

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.app.classbook.R

/**
 * This class is used for storing and retrieving shared preference values.
 */
class AppPreference(context: Context) {

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private val pref = EncryptedSharedPreferences
        .create(
            context.getString(R.string.app_name),
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )


    var isLogin: Boolean
        get() = pref.getBoolean("isLogin", false)
        set(value) = pref.edit().putBoolean("isLogin", value).apply()

    /**
     * Store and get firebase token
     */
    var deviceToken: String?
        get() = pref.getString("deviceToken", "")
        set(value) = pref.edit().putString("deviceToken", value).apply()

    /**
     * Store and get logged-in user auth token, which is used for calling apis
     */
    var authToken: String?
        get() = pref.getString("authToken", "Default")
        set(value) = pref.edit().putString("authToken", value).apply()


    var userId: Int
        get() = pref.getInt("userId", 0)
        set(value) = pref.edit().putInt("userId", value).apply()


    var userName: String?
        get() = pref.getString("userName", "")
        set(value) = pref.edit().putString("userName", value).apply()


    var token: String?
        get() = pref.getString("token", "")
        set(value) = pref.edit().putString("token", value).apply()
}