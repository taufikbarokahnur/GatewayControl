package com.example.gatewaycontrol.api

import com.example.gatewaycontrol.ApiRequest
import com.example.gatewaycontrol.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    val  apiScarlar = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(ApiRequest::class.java)

    val apiGson = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiRequest::class.java)

}