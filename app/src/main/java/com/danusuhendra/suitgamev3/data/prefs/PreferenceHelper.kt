package com.danusuhendra.suitgamev3.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.danusuhendra.suitgamev3.utils.*

class PreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            PREF_NAME, PRIVATE_MODE
        )

    var isLogin: Boolean
        get() {
            return sharedPreferences.getBoolean(STATUS, false)
        }
        set(value) {
            sharedPreferences.edit().apply {
                putBoolean(STATUS, value)
                apply()
            }
        }

    var username: String?
        get() {
            return sharedPreferences.getString(USERNAME, "")
        }
        set(value) {
            sharedPreferences.edit().apply {
                putString(USERNAME, value)
                apply()
            }
        }

    var token: String?
        get() {
            return sharedPreferences.getString(TOKEN, "")
        }
        set(value) {
            sharedPreferences.edit().apply {
                putString(TOKEN, value)
                apply()
            }
        }

    var userId: String?
        get() {
            return sharedPreferences.getString(USER_ID, "")
        }
        set(value) {
            sharedPreferences.edit().apply {
                putString(USER_ID, value)
                apply()
            }
        }
}