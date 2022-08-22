package com.example.mvvm.ui.viewModel.GithubViewModel

import android.net.wifi.aware.DiscoverySession
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.api.Client
import com.example.mvvm.data.model.Users
import com.example.mvvm.data.repo.GithubRepository
import kotlinx.coroutines.*
import androidx.lifecycle.liveData

class GithubViewModel : ViewModel() {

  val users = MutableLiveData<List<Users>>()

    fun fetchUsers(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                  GithubRepository.getUsers()
            }

            if(response.isSuccessful){
                response.body()?.let{
                    users.postValue(it)
                }
            }

        }
    }

    fun searchUsers(name : String) = liveData(Dispatchers.IO){
        val response = withContext(Dispatchers.IO){GithubRepository.searchUsers(name)}

        if(response.isSuccessful){
            response.body()?.let{
                emit(it.items)
            }
        }

    }
}


//Extension function for search users

fun ViewModel.runIO(dispatchers : CoroutineDispatcher = Dispatchers.IO ,
function: suspend CoroutineScope.() -> Unit){
      viewModelScope.launch(dispatchers) {
          function()
      }
}