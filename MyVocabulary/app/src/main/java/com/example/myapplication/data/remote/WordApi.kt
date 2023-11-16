package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.dto.wordValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface wordApi {
    @GET("/api/v2/entries/en/{word}")
    suspend fun getwordMeans(@Path("word") word:String):List<wordValue>
}