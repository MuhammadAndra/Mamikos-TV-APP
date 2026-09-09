package com.example.mamikostvapp.data.network.api_service

import com.example.mamikostvapp.data.model.Show
import com.example.mamikostvapp.navigation.nav_graph.ShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ShowApiService {
    @GET("/shows")
    suspend fun getShow(
        @Query("page")
        page: Int
    ): Response<List<Show>>
}