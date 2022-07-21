package com.lvlin.mvidemo.ui.view

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lvlin.mvidemo.R
import com.lvlin.mvidemo.data.api.ApiHelperImpl
import com.lvlin.mvidemo.data.api.RetrofitBuilder
import com.lvlin.mvidemo.data.model.User
import com.lvlin.mvidemo.ui.adapter.MainAdapter
import com.lvlin.mvidemo.ui.intent.MainIntent
import com.lvlin.mvidemo.ui.viewmodel.MainViewModel
import com.lvlin.mvidemo.ui.viewstate.MainState
import com.lvlin.mvidemo.util.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
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
        setupViewModel()
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
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        buttonFetchUser.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE
                        renderList(it.user)
                    }

                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        recyclerview.visibility = View.VISIBLE
        users.let { listofUsers -> listofUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}