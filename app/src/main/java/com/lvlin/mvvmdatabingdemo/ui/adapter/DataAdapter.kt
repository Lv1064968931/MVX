package com.lvlin.mvvmdatabingdemo.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @author: lvlin
 * @email: lin2.lv@lvin.com
 * @date: 2022/7/22
 */
class DataAdapter {

    companion object{

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setSrc(imageView: ImageView, url: String){
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }
}