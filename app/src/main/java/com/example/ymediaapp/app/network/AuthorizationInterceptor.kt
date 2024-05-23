package com.example.ymediaapp.app.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "x-goog-api-key",
                "AIzaSyCTzGpYqvQaLJVaClM17hp9NLSIaPJTyug"
            )
            .build()
        return chain.proceed(newRequest)
    }
}