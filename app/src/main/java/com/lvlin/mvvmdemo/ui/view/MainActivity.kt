package com.lvlin.mvvmdemo.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lvlin.mvidemo.data.model.User
import com.lvlin.mvvmdemo.R
import com.lvlin.mvvmdemo.ui.adapter.MainAdapter
import com.lvlin.mvvmdemo.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private var adapter = MainAdapter(arrayListOf())

    private lateinit var buttonFetchUser: Button
    private lateinit var recyclerview: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonFetchUser = findViewById(R.id.buttonFetchUser)
        recyclerview = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        setupUI()
        observeViewModel()
        setupClicks()
    }

    private fun setupUI() {
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerview.context,
                    (recyclerview.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerview.adapter = adapter
    }

    private fun setupClicks() {
        buttonFetchUser.setOnClickListener {
                buttonFetchUser.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                viewModel.fetchUsers()
        }
    }

    private fun observeViewModel() {
            viewModel._users.observe(this@MainActivity,{
                val users = it
                if(users != null) {
                    progressBar.visibility = View.GONE
                    renderList(users)
                }
            })
    }

    private fun renderList(users: List<User>) {
        recyclerview.visibility = View.VISIBLE
        Log.d(TAG, "renderList =$users")
        users.let { listofUsers -> listofUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}