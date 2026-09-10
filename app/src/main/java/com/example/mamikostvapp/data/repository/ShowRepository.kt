package com.example.mamikostvapp.data.repository

import com.example.mamikostvapp.data.DataResult
import com.example.mamikostvapp.data.model.Show
import com.example.mamikostvapp.data.network.NetworkModule
import com.example.mamikostvapp.data.network.api_service.ShowApiService
import javax.inject.Inject

class ShowRepository @Inject constructor(
    private val api: ShowApiService,
) : ShowRepositoryInterface,
    BaseRepository() {

    override suspend fun getShows(page: Int): DataResult<List<Show>> {
        val result = safeApiCall { api.getShow(page) }
        return result
    }

    override suspend fun getShowDetail(id: Int): DataResult<Show> {
        val result = safeApiCall { api.getShowDetail(id) }
        return result
    }
}