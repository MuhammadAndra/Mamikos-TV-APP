package com.example.mamikostvapp.data.repository

import com.example.mamikostvapp.data.DataResult
import com.example.mamikostvapp.data.model.Show
import com.example.mamikostvapp.data.network.RetrofitClient

class ShowRepository: BaseRepository() {
    private val api = RetrofitClient.showApi
    suspend fun getShows(page: Int): DataResult<List<Show>> {
        val result = safeApiCall { api.getShow(page) }
        return result
    }
}