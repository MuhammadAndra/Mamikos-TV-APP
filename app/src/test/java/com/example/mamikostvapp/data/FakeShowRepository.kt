package com.example.mamikostvapp.data

import com.example.mamikostvapp.data.model.Country
import com.example.mamikostvapp.data.model.Externals
import com.example.mamikostvapp.data.model.Image
import com.example.mamikostvapp.data.model.Links
import com.example.mamikostvapp.data.model.Network
import com.example.mamikostvapp.data.model.PreviousEpisode
import com.example.mamikostvapp.data.model.Rating
import com.example.mamikostvapp.data.model.Schedule
import com.example.mamikostvapp.data.model.Self
import com.example.mamikostvapp.data.model.Show
import com.example.mamikostvapp.data.repository.ShowRepositoryInterface

class FakeShowRepository(
    private val showsResult: DataResult<List<Show>> = DataResult.Empty,
    private val detailResult: DataResult<Show> = DataResult.Empty
) : ShowRepositoryInterface {

    override suspend fun getShows(page: Int): DataResult<List<Show>> {
        return showsResult
    }

    override suspend fun getShowDetail(id: Int): DataResult<Show> {
        return detailResult
    }
}
