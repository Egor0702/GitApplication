package com.example.gitapplication

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gitapplication.data.bd.retrofit.GetRepositories
import com.example.gitapplication.presenter.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl{
    val presenter = Presenter()
    val TAG = "Egor"
    fun getRequest(repostoriesName : String, context: Context) {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val retrofitObj : GetRepositories = retrofitBuilder.create(GetRepositories::class.java)
        val single = retrofitObj.getRepositories(repostoriesName)
        single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       Log.d(TAG, "complete")
                presenter.getList(it)
            },{
                Log.d(TAG, "${it.message}")
                Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
            })
    }
}
