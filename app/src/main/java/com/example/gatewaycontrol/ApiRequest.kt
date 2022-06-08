package com.example.gatewaycontrol

import com.example.gatewaycontrol.api.MultipleData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {
    @GET("get?token=QsKCoJGBB6-hCoYUb7ukdHfoKxd0aX2r&v21&v22&v23")
    fun getIR() : Call<MultipleData>

    @GET("get?token=QsKCoJGBB6-hCoYUb7ukdHfoKxd0aX2r&v10")
    fun getRF() : Call<String>

    @GET("update?token=QsKCoJGBB6-hCoYUb7ukdHfoKxd0aX2r")
    fun sendRF(@Query("v11") binary : String) : Call<String>

    @GET("batch/update?token=QsKCoJGBB6-hCoYUb7ukdHfoKxd0aX2r")
    fun sendIR(
        @Query("v21") Protocol : String,
        @Query("v22") Value: String,
        @Query("v23") Length: String
    ) : Call<String>
}