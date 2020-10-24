package com.example.testapp

import android.app.Application
import androidx.room.Room


class App: Application() {

    companion object {
        lateinit var storage: SheredPrefs
        lateinit var db:AppDatabase
        var currentProduct:Product? = null
    }


    override fun onCreate() {
        super.onCreate()
        storage = SheredPrefs(applicationContext, "login_preferences")
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "hobo-database"
        ).allowMainThreadQueries().build()

    }
}