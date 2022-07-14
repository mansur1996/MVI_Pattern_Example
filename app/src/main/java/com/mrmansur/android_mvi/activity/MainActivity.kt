package com.mrmansur.android_mvi.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mrmansur.android_mvi.activity.halper.MainHelperImpl
import com.mrmansur.android_mvi.activity.intentstate.MainIntent
import com.mrmansur.android_mvi.activity.intentstate.MainState
import com.mrmansur.android_mvi.activity.viewmodel.MainViewModel
import com.mrmansur.android_mvi.activity.viewmodel.MainViewModelFactory
import com.mrmansur.android_mvi.adapter.PostAdapter
import com.mrmansur.android_mvi.databinding.ActivityMainBinding
import com.mrmansur.android_mvi.model.Post
import com.mrmansur.android_mvi.network.RetrofitBuilder
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch{
            viewModel.state.collect{
                when(it){
                    is MainState.Init -> {
                        Log.d("MainActivity", "Init")
                    }

                    is MainState.Loading -> {
                        Log.d("MainActivity", "Loading")
                    }

                    is MainState.AllPosts -> { it
                        Log.d("MainActivity", "AllPosts ${it.posts.size}")
                        refreshAdapter(it.posts)
                    }

                    is MainState.DeletePost -> { it
                        Log.d("MainActivity", "AllPosts ${it.post}")
                        intentAllPosts()
                    }

                    is MainState.Error -> { it
                        Log.d("MainActivity", "AllPosts $it")
                    }
                }
            }
        }

    }

    private fun initViews() {
        val factory = MainViewModelFactory(MainHelperImpl(RetrofitBuilder.POST_SERVICE))
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        intentAllPosts()
    }

    private fun refreshAdapter(posters: ArrayList<Post>) {
        val adapter = PostAdapter(this, posters)
        binding.recyclerView.adapter = adapter
    }

    private fun intentAllPosts(){
        lifecycleScope.launch{
            viewModel.mainIntent.send(MainIntent.AllPosts)
        }
    }

    fun intentDeletePost(id: Int) {
        viewModel.postId = id
        lifecycleScope.launch{
            viewModel.mainIntent.send(MainIntent.DeletePost)
        }
    }
}