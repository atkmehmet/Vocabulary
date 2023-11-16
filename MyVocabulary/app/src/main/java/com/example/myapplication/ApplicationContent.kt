package com.example.myapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationContent:Application () {

    init {
         app=this
    }

    companion object{
          private  lateinit var app:Application
          fun getAppContext():Context= app.applicationContext
    }
}