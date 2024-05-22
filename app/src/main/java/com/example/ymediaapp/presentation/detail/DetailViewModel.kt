package com.example.ymediaapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ymediaapp.domain.repository.VideoRepository
import com.example.ymediaapp.presentation.model.YoutubeVideoModel
import com.example.ymediaapp.presentation.model.toEntity
import com.example.ymediaapp.presentation.model.toModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

class DetailViewModel(private val repository: VideoRepository) : ViewModel() {

    private val _selectedItem = MutableLiveData<YoutubeVideoModel>()
    val selectedItem: LiveData<YoutubeVideoModel> get() = _selectedItem

    private val _shareItem = MutableLiveData<YoutubeVideoModel?>()

    val shareItem: LiveData<YoutubeVideoModel?> get() = _shareItem

    private val _isLikeStatus = MutableLiveData<Boolean>()
    val isLikeStatus: LiveData<Boolean> get() = _isLikeStatus


//    fun toggleLike() {
//        selectedItem.value.let {
//
//        }
//        if (selectedItem.value != null) {
//            //let 으로 바꾸기, it
//            // isLike를 데이터베이스에 있는지 없는지를 가지고 livedata 달아주기
//
//            _selectedItem.value = selectedItem.value!!.copy(isLike = !selectedItem.value!!.isLike)
//            viewModelScope.launch {
//                repository.insertVideoData(selectedItem.value!!.toEntity())
//            }
//        }
//    }

//    fun toggleLike() {
//        _selectedItem.value?.let { selectedItemModel ->
//            val isCurrentlyLiked = _isLikeStatus.value ?: false
//            val updatedLikeStatus = !isCurrentlyLiked
//            _isLikeStatus.value = updatedLikeStatus
//
//            val selectedItemEntity = selectedItemModel.toEntity()
//
//            viewModelScope.launch {
//                if (updatedLikeStatus) {
//                    // 좋아요 상태가 변경되었으므로 데이터베이스에 추가
//                    repository.insertVideoData(selectedItemEntity)
//                } else {
//                    // 좋아요 상태가 변경되었으므로 데이터베이스에서 삭제
//                    repository.deleteVideoData(selectedItemEntity)
//                }
//            }
//        }
//    }

    fun toggleLike() {
        selectedItem.value?.let { item ->
            viewModelScope.launch {
                val videoEntity = repository.getDataById(item.videoId)
                if (videoEntity == null) {
                    repository.insertVideoData(item.toEntity())
                    _isLikeStatus.postValue(true)
                } else {
                    repository.deleteVideoData(item.toEntity())
                    _isLikeStatus.postValue(false)
                }
            }
        }
    }

//    init {
//        // isLikeStatus 초기화
//        _selectedItem.observeForever { selectedItem ->
//            viewModelScope.launch {
//                val videoEntity = repository.getDataById(selectedItem.videoId)?.toModel()
//                _isLikeStatus.value = videoEntity != null
//            }
//        }
//    }
//
////    fun setIsLikeStatus(selectedItem: YoutubeVideoModel) {
////        viewModelScope.launch {
////            val videoEntity = repository.getDataById(selectedItem.videoId)
////            _isLikeStatus.value = videoEntity != null
////        }
////    }

//    fun setSelectedItem(selectedItem: YoutubeVideoModel) {
//        viewModelScope.launch {
//            val videoEntity = repository.getDataById(selectedItem.videoId)
//            if (videoEntity == null) {
//                _selectedItem.value = selectedItem
//            } else {
//                videoEntity?.let { _selectedItem.value = it.toModel() }
//            }
//        }
//    }

    fun setSelectedItem(selectedItem: YoutubeVideoModel) {
        _selectedItem.value = selectedItem
        viewModelScope.launch {
            val videoEntity = repository.getDataById(selectedItem.videoId)
            _isLikeStatus.postValue(videoEntity != null)
        }
    }

    fun setShareItem(selectedItem: YoutubeVideoModel?) {
        _shareItem.value = selectedItem
    }

    fun shareItem() {
        _shareItem.value = _selectedItem.value
    }
}

class DetailViewModelFactory(private val videoRepository: VideoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(
                repository = videoRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
