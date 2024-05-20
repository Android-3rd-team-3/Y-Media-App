package com.example.ymediaapp.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "x-goog-api-key",
                "AIzaSyCAOdeHz6erGcY_sbcEqbEgAETVpirfiV8"
            )
            .build()
        return chain.proceed(newRequest)
    }
}