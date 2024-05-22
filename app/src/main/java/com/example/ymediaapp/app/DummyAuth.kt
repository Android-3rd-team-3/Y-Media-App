package com.example.ymediaapp.app

import androidx.annotation.DrawableRes
import com.example.ymediaapp.R

object DummyAuth {
    private val currentUser = User(
        channelName = "아보카도",
        channelDescription = "양식당 경력 15년차\n올리고 싶은 레시피를 가끔 올립니다\n문의: asd@asd.com",
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