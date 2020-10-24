package com.example.testapp


import android.content.Context
import android.content.SharedPreferences



class SheredPrefs(context: Context, fileName:String) {
    private val PREFS_FILENAME = fileName
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME,0)
    private val IS_LOGGED_IN = "isLoggedIn"
    private val USER_EMAIL = "userEmail"
    private val USER_NAME = "userName"


    var isLoggedIn:Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN,false)
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN,value).apply()



    var myName:String?
        get() = prefs.getString(USER_NAME,"")
        set(value) = prefs.edit().putString(USER_NAME,value).apply()

    var myEmail:String?
        get() = prefs.getString(USER_EMAIL,"")
        set(value) = prefs.edit().putString(USER_EMAIL,value).apply()



}