package com.example.ymediaapp.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "x-goog-api-key",
                "AIzaSyCiMgoVsaUNtMh2pws6geCEU7Mu-rK46rg"
            )
            .build()
        return chain.proceed(newRequest)
    }
}