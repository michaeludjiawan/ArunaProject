package com.michaeludjiawan.arunaproject.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreferences private constructor() {

    fun save(key: String, value: Long) {
        sharedPref.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defValue: Long = 0): Long {
        return sharedPref.getLong(key, defValue)
    }

    fun contains(key: String): Boolean {
        return sharedPref.contains(key)
    }

    companion object {
        private lateinit var sharedPref: SharedPreferences

        private var INSTANCE: AppPreferences = AppPreferences()

        fun getInstance(context: Context): AppPreferences {
            if (!this::sharedPref.isInitialized) {
                sharedPref = context.getSharedPreferences("app", Context.MODE_PRIVATE)
            }

            return INSTANCE
        }
    }
}