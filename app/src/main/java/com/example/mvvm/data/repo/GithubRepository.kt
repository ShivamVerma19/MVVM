package com.example.mvvm.data.repo

import com.example.mvvm.data.api.Client

object GithubRepository {

    suspend fun getUsers() = Client.api.getUsers()


    suspend fun searchUsers(name : String) = Client.api.searchUsers(name)
}