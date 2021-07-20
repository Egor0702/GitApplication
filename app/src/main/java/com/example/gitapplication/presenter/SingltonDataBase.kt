package com.example.gitapplication.presenter

import android.content.Context
import androidx.room.Room
import com.example.gitapplication.RetrofitImpl
import com.example.gitapplication.data.bd.DataBaseApp
import com.example.gitapplication.data.bd.RepositoryDao
import com.example.gitapplication.data.bd.download.DownloadRepository

class SingltonDataBase {
    companion object{
        lateinit var dataBaseApp: DataBaseApp
        lateinit var repositoryDao: RepositoryDao
        lateinit var retrofitImpl: RetrofitImpl
        lateinit var downloadRepository: DownloadRepository
        fun getInstanceDataBaseApp(context: Context): DataBaseApp {
            if(!Companion::dataBaseApp.isInitialized){
                dataBaseApp = Room.databaseBuilder(context, DataBaseApp::class.java, "database").build()
            }
            return dataBaseApp
        }
        fun getInstanceRepositoryDao(context: Context): RepositoryDao{
            if(!Companion::repositoryDao.isInitialized){
                if(!Companion::dataBaseApp.isInitialized)
                    getInstanceDataBaseApp(context)
                repositoryDao = dataBaseApp.repositoryDao()
            }
            return repositoryDao
        }
        fun getInstanceRetrofitImpl(): RetrofitImpl{
            if(!Companion::retrofitImpl.isInitialized){
                retrofitImpl = RetrofitImpl()
            }
            return retrofitImpl
        }
        fun getInstanceDownloadRepository(): DownloadRepository{
            if(!Companion::downloadRepository.isInitialized){
                downloadRepository = DownloadRepository()
            }
            return downloadRepository
        }
    }
}