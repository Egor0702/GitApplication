package com.example.gitapplication.presenter

import android.content.Context
import androidx.room.Room
import com.example.gitapplication.data.bd.DataBaseApp

class SingltonDataBase {
    companion object{
        lateinit var dataBaseApp: DataBaseApp
        fun getInstance(context: Context): DataBaseApp {
            if(!Companion::dataBaseApp.isInitialized){
                dataBaseApp = Room.databaseBuilder(context, DataBaseApp::class.java, "database").build()
            }
            return dataBaseApp
        }
    }
}