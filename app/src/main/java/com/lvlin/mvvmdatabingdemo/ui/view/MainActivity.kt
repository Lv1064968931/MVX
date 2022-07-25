package com.lvlin.mvvmdatabingdemo.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvlin.mvvmdatabingdemo.R
import com.lvlin.mvvmdatabingdemo.data.model.User
import com.lvlin.mvvmdatabingdemo.databinding.ActivityMainBinding
import com.lvlin.mvvmdatabingdemo.ui.adapter.MainAdapter
import com.lvlin.mvvmdatabingdemo.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var databinding: ActivityMainBinding
    private var adapter = MainAdapter(arrayListOf())
    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setupUI()
        observeViewModel()
        setupClicks()
    }

    private fun setupUI() {
        databinding.recyclerView.layoutManager = LinearLayoutManager(this)
        databinding.recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    databinding.recyclerView.context,
                    (databinding.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        databinding.recyclerView.adapter = adapter
    }

    private fun setupClicks() {
        databinding.buttonFetchUser.setOnClickListener {
                databinding.buttonFetchUser.visibility = View.GONE
                databinding.progressBar.visibility = View.VISIBLE
                viewModel.fetchUsers()
        }
    }

    private fun observeViewModel() {
            viewModel._users.observe(this@MainActivity,{
                val users = it
                if(users != null) {
                    databinding.progressBar.visibility = View.GONE
                    renderList(users)
                }
            })
    }

    private fun renderList(users: List<User>) {
        databinding.recyclerView.visibility = View.VISIBLE
        users.let { listofUsers -> listofUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}