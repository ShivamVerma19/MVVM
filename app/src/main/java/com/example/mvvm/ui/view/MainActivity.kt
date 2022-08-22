package com.example.mvvm.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.model.Users
import com.example.mvvm.ui.Adapter.UserAdapter
import com.example.mvvm.ui.viewModel.GithubViewModel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val vm by lazy{
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }

    val list  = ArrayList<Users>()
    val originalList = ArrayList<Users>()
    val adapter = UserAdapter(list)

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }

        
        searchView.isSubmitButtonEnabled = true 
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{
                    findUser(it)
                }
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text?.let{
                    findUser(it)
                }
                return true
            }

        })

        searchView.setOnCloseListener {
            list.clear()
            list.addAll(originalList)
           // vm.users.value?.let{list.addAll(it)}
            adapter.notifyDataSetChanged()
            return@setOnCloseListener true
        }
        
        vm.fetchUsers()

        vm.users.observe(this , Observer {
            Log.e("Size" , it.size.toString())
            if(!it.isNullOrEmpty()){
                list.addAll(it)
                originalList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun findUser(query: String) {
         vm.searchUsers(query).observe(this , Observer {

             if(!it.isNullOrEmpty()){
                 list.clear()
                 list.addAll(it)
                 adapter.notifyDataSetChanged()
             }
         })
    }
}