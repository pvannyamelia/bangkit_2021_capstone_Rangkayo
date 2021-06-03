package com.capstone.rangkayo.data.source.remote.api

import com.capstone.rangkayo.data.source.remote.response.Responses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface Service {
    @GET("search")
    fun getNews(
        @Header("x-rapidapi-key") api_key: String,
        @Header("x-rapidapi-host") api_host: String,
        @Query("q") q: String
    ): Call<Responses>
}