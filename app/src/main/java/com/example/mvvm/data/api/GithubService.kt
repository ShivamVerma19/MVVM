package com.example.mvvm.data.api

import com.example.mvvm.data.model.SearchResponse
import com.example.mvvm.data.model.Users
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("users")
    suspend fun getUsers() : Response<List<Users>>

    @GET("search/users")
    suspend fun searchUsers(@Query("q") name : String) : Response<SearchResponse>
}