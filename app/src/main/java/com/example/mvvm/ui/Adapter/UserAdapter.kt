package com.example.mvvm.ui.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.model.Users
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class UserAdapter(val data : List<Users>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent , false)

        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Log.e("TAG2" , data.size.toString())
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        Log.e("TAG1" , data.size.toString())
        return data.size
    }


    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(users: Users) {
            with(itemView){
                textView.text = users.login
                Picasso.get().load(users.avatarUrl).into(imageView)
            }
        }

    }
}

