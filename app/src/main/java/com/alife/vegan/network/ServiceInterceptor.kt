package com.alife.vegan.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {
    companion object {
        var basic = Credentials.basic("hc2@naver.com", "testtest!")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
//        var request = chain.request()
//        if (request.header("No-Authentication") == null) {
//
//            if (!userToken.isNullOrEmpty()) {
//                val finalToken = "$userToken"
//                request = request.newBuilder().apply {
//                    addHeader("Authorization", finalToken)
//                }.build()
//            }
//        }
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", basic)
            .build()
        return chain.proceed(newRequest)
    }

}