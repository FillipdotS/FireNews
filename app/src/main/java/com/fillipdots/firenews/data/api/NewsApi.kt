package com.fillipdots.firenews.data.api

import com.fillipdots.firenews.data.api.dto.ApiArticlesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Header("X-Api-Key") apiKey: String,
        @QueryMap queries: Map<String, String>
    ): ApiArticlesDto

    @GET("everything")
    suspend fun getEverything(
        @Header("X-Api-Key") apiKey: String,
        @QueryMap queries: Map<String, String>
    ): ApiArticlesDto
}