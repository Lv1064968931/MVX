package com.lvlin.mvidemo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lvlin.mvidemo.R
import com.lvlin.mvidemo.data.model.User

/**
 * @author: lvlin
 * @email: lin2.lv@lvlin.com
 * @date: 2022/7/12
 */
class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {


    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewUserName: TextView = itemView.findViewById(R.id.textViewUserName)
        var imageViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        var textViewUserEmail: TextView = itemView.findViewById(R.id.textViewUserEmail)


        fun bind(user: User) {
            textViewUserName.text = user.name
            textViewUserEmail.text = user.email
            Glide.with(imageViewAvatar.context)
                .load(user.avatar)
                .into(imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}