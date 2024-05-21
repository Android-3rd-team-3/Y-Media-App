package com.example.ymediaapp.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.ymediaapp.R
import com.example.ymediaapp.app.DetailViewModelFactory
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.data.database.YoutubeRoomDatabase
import com.example.ymediaapp.data.repository.VideoRepositoryImpl
import com.example.ymediaapp.databinding.ActivityMainBinding
import com.example.ymediaapp.presentation.detail.DetailFragment
import com.example.ymediaapp.presentation.detail.DetailViewModel
import com.example.ymediaapp.domain.entity.YoutubeVideoEntity
import com.example.ymediaapp.presentation.home.FragmentDataListener
import com.example.ymediaapp.domain.repository.VideoRepository

class MainActivity : AppCompatActivity(), FragmentDataListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DetailViewModel

    //    private lateinit var repository: VideoRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val repository = VideoRepositoryImpl(this)
//        val viewModelFactory = DetailViewModelFactory(repository)

        //viewModel = (this.application as YMediaApplication).appContainer.detailViewModelFactory.create()

        //viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(DetailViewModel::class.java)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        //네비게이션을 담는 호스트
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_containerView) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바텀 네비게이션 뷰 와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(binding.navigationView, navController)


    }

    override fun onDataReceived(data: YoutubeVideoEntity) {
        viewModel.setSelectedItem(data)
        showBottomSheet()
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = DetailFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}