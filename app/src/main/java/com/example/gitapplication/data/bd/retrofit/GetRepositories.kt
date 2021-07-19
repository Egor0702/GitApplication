package com.example.gitapplication.data.bd.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GetRepositories {
    @GET("users/{USERNAME}/repos")
    fun getRepositories(@Path("USERNAME") repositoryName: String): Single<List<DateRepository>>
}