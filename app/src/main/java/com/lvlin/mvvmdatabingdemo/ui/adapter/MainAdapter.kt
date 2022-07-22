package com.lvlin.mvvmdatabingdemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lvlin.mvvmdatabingdemo.R
import com.lvlin.mvvmdatabingdemo.data.model.User
import com.lvlin.mvvmdatabingdemo.databinding.ItemLayoutBinding

/**
 * @author: lvlin
 * @email: lin2.lv@lvin.com
 * @date: 2022/7/22
 */
class MainAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private var binding : ItemLayoutBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(user: User){
            binding.user = user
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutBinding>(LayoutInflater.from(parent.context), R.layout.item_layout, parent,false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}