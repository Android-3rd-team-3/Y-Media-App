package com.example.ymediaapp.presentation.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.ymediaapp.R
import com.example.ymediaapp.app.AppContainer
import com.example.ymediaapp.app.DetailContainer
import com.example.ymediaapp.app.MyVideoContainer
import com.example.ymediaapp.app.YMediaApplication
import com.example.ymediaapp.databinding.ActivityMainBinding
import com.example.ymediaapp.presentation.detail.DetailFragment
import com.example.ymediaapp.presentation.detail.DetailViewModel
import com.example.ymediaapp.presentation.home.FragmentDataListener
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.my_video.MyVideoViewModel

class MainActivity : AppCompatActivity(), FragmentDataListener {

    private lateinit var appContainer: AppContainer
    private lateinit var detailViewModel: DetailViewModel

    private lateinit var binding: ActivityMainBinding
//    private val detailViewModel by lazy {
//        (this.application as YMediaApplication).detailViewModelFactory.create()
//    }




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

        //네비게이션을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_containerView) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바텀 네비게이션 뷰 와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(binding.navigationView, navController)


        appContainer = (this.application as YMediaApplication).appContainer
        appContainer.detailContainer = DetailContainer(appContainer.videoRepository)
        appContainer.detailContainer?.let {
            detailViewModel = ViewModelProvider(this, it.detailViewModelFactory)[DetailViewModel::class.java]
        }

    }

    override fun onDataReceived(data: YoutubeVideoModel) {
        detailViewModel.setSelectedItem(data)
        showBottomSheet()
    }

    private fun showBottomSheet() {
        val bottomSheetFragment = DetailFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}