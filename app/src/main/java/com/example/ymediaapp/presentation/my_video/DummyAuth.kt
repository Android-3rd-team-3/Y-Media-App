package com.example.ymediaapp.presentation.my_video

import androidx.annotation.DrawableRes
import com.example.ymediaapp.R

object DummyAuth {
    private val currentUser = User(
        channelName = "아보카도",
        channelDescription = "채널 설명 채널 설명 채널 설명 채널 설명 \n채널 설명 채널 설명 채널 설명 채널 설명 채널 설명 채널 설명",
        profileImage = R.drawable.dummy_profile,
        backgroundImage = R.drawable.dummy_background
    )

    fun getUser() = currentUser

}

data class User(
    val channelName: String,
    val channelDescription: String,
    @DrawableRes val profileImage: Int,
    @DrawableRes val backgroundImage: Int,
)