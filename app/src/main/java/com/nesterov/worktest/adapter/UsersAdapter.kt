package com.nesterov.worktest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nesterov.worktest.R
import com.nesterov.worktest.databinding.ListItemBinding
import com.nesterov.worktest.retrofit.Users
import com.squareup.picasso.Picasso

class UsersAdapter: ListAdapter<Users,UsersAdapter.Holder>(Comparator()) {

    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ListItemBinding.bind(view)

        fun bind(users: Users) = with(binding){

            textLogin.text = users.login
            Picasso.get().load(users.avatar_url).into(avatarUrlImage)
        }
    }

    class Comparator: DiffUtil.ItemCallback<Users>(){
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}