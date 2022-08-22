package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    val adapter = UserAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        vm.fetchUsers()

        vm.users.observe(this , Observer {
            if(it.isNullOrEmpty()){
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}