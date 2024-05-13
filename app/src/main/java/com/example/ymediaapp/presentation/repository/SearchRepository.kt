package com.example.ymediaapp.presentation.repository

interface SearchRepository {
    suspend fun getSearchList(searchWord: String)
}