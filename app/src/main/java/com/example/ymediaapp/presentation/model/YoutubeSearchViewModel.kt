package com.example.ymediaapp.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ymediaapp.data.repository.SearchRepositoryImpl
import com.example.ymediaapp.network.RetrofitClient
import com.example.ymediaapp.presentation.repository.SearchRepository

class YoutubeSearchViewModel(private val repository: SearchRepositoryImpl): ViewModel() {

}

class YoutubeSearchViewModelFactory: ViewModelProvider.Factory{
    private val repository = SearchRepositoryImpl(RetrofitClient.youtubeService)

    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return YoutubeSearchViewModel(repository) as T
    }

}