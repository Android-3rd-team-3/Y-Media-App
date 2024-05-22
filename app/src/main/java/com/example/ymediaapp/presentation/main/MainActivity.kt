package com.example.ymediaapp.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.ymediaapp.R
import com.example.ymediaapp.databinding.ActivityMainBinding
import com.example.ymediaapp.presentation.detail.DetailFragment
import com.example.ymediaapp.presentation.model.YoutubeVideoModel

class MainActivity : AppCompatActivity(){

    private lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

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

        mainViewModel=ViewModelProvider(this).get(MainViewModel::class.java)

        // LiveData 관찰
        mainViewModel.selectedItem.observe(this) {
            it?.let {
                try {
                    showBottomSheet(it)
                    Log.d("main 1", "$it")
                }
                finally {
                    Log.d("main 2","$it")
                    mainViewModel.setSelectedItem(null)
                }
            }
        }
    }

    private fun showBottomSheet(data: YoutubeVideoModel) {
        val bottomSheetFragment = DetailFragment()
        bottomSheetFragment.arguments= bundleOf("clickItem" to data)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}