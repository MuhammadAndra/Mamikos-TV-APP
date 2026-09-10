package com.example.mamikostvapp.data.repository

import com.example.mamikostvapp.data.DataResult
import com.example.mamikostvapp.data.model.Cast
import com.example.mamikostvapp.data.model.Season
import com.example.mamikostvapp.data.model.Show

interface ShowRepositoryInterface {
    suspend fun getShowDetail(id: Int): DataResult<Show>
    suspend fun getShows(page: Int): DataResult<List<Show>>
    suspend fun getSeasons(id:Int): DataResult<List<Season>>
    suspend fun getCasts(id:Int): DataResult<List<Cast>>
}