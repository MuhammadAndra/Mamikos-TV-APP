package com.example.mamikostvapp.data.network.api_service

import com.example.mamikostvapp.data.model.Cast
import com.example.mamikostvapp.data.model.Season
import com.example.mamikostvapp.data.model.Show
import com.example.mamikostvapp.navigation.nav_graph.ShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowApiService {
    @GET("/shows")
    suspend fun getShow(
        @Query("page")
        page: Int
    ): Response<List<Show>>

    @GET("/shows/{id}")
    suspend fun getShowDetail(
        @Path("id")
        id: Int
    ): Response<Show>

    @GET("/shows/{id}/seasons")
    suspend fun getShowSeasons(
        @Path("id")
        id: Int
    ): Response<List<Season>>

    @GET("/shows/{id}/cast")
    suspend fun getShowCasts(
        @Path("id")
        id: Int
    ): Response<List<Cast>>
}