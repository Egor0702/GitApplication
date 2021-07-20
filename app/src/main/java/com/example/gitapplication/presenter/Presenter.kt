package com.example.gitapplication.presenter

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.gitapplication.RetrofitImpl
import com.example.gitapplication.data.bd.EntityDate
import com.example.gitapplication.data.bd.download.DownloadRepository
import com.example.gitapplication.data.bd.retrofit.DateRepository
import com.example.gitapplication.ui.MainActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.util.concurrent.Callable
import java.util.concurrent.Executors


class Presenter {
    companion object{
        var mainActivity: MainActivity? = null
        var number = 1
    }
    fun getDataBD(account: String, repName: String, context: Context) {
        val repositoryDao = SingltonDataBase.getInstanceRepositoryDao(context)
            val entityDate = EntityDate(
                id = number,
                nameAccount = account,
                nameRepository = repName,
            )
        val call: Callable<Unit> = Callable<Unit> {repositoryDao.insert(entityDate) }
        val future = Executors.newSingleThreadExecutor().submit(call)
        number++
        }

    fun getAll(context: Context): List<EntityDate>{
        val repositoryDao = SingltonDataBase.getInstanceRepositoryDao(context)
        val call: Callable<List<EntityDate>> = Callable<List<EntityDate>> {repositoryDao.getAll() }
        val future = Executors.newSingleThreadExecutor().submit(call)
        return future.get()
        }

    fun getList(list: List<DateRepository>){
        var listForSpinner = mutableListOf<String>()
        var mapForDown = mutableMapOf<String, String>()
        for (i in list){
            listForSpinner.add(i.name)
            mapForDown.put(i.name, i.default_branch)
        }
        mainActivity!!.setSpinner(listForSpinner)
        mainActivity!!.mapForDownload = mapForDown
    }
    fun openURL(accName: String, repositoryName: String, context: Context){
        var uri = Uri.parse("https://github.com/$accName/$repositoryName")
        var intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
    fun downloadFile(
        accountName: String,
        repositoryName: String,
        defaultBranch: String,
        manager: DownloadManager,
        context: Context,
        activity: Activity
    ){
        val downloadRep = DownloadRepository()
        downloadRep.downloadFile(
            accountName,
            repositoryName,
            defaultBranch,
            manager,
            context,
            activity
        )
    }

    fun sendRequest(name: String, obj: MainActivity, context : Context) {
        val retrofitImpl = SingltonDataBase.getInstanceRetrofitImpl()
        mainActivity = obj
        retrofitImpl.getRequest(name, context)

    }
    fun requestPermission(){
        val downloadRepository = SingltonDataBase.getInstanceDownloadRepository()
        downloadRepository.requestermissionsAction()
    }

}