package com.example.mamikostvapp.ui.list

import com.example.mamikostvapp.data.DataResult
import com.example.mamikostvapp.data.FakeShowRepository
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
import com.example.mamikostvapp.ui.screen.show_list.ShowListViewmodel
import com.example.saferecycle.ui.state.AppError
import com.example.saferecycle.ui.state.UiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

class ShowListViewmodelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `loadShows emits Success when repository succeeds`() = runTest {

        val fakeShows = listOf(
            createFakeShow()
        )

        val fakeRepository = FakeShowRepository(
            showsResult = DataResult.Success(fakeShows)
        )

        val viewModel = ShowListViewmodel(
            repository = fakeRepository
        )

        viewModel.loadShows()

        advanceUntilIdle()

        assertEquals(
            UiState.Success(fakeShows),
            viewModel.shows.value
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `loadShows emits Error when repository fails`() = runTest {

        val fakeError = AppError.Network(
            message = "No internet connection"
        )

        val fakeRepository = FakeShowRepository(
            showsResult = DataResult.Error(fakeError)
        )

        val viewModel = ShowListViewmodel(
            repository = fakeRepository
        )

        viewModel.loadShows()

        advanceUntilIdle()

        assertEquals(
            UiState.Error(fakeError),
            viewModel.shows.value
        )
    }
}

private fun createFakeShow(): Show {
    return Show(
        id = 1,
        url = "https://www.tvmaze.com/shows/1/fake-show",
        name = "Fake Show",
        type = "Scripted",
        language = "English",
        genres = listOf("Drama"),
        status = "Running",
        runtime = 60,
        averageRuntime = 60,
        premiered = "2026-01-01",
        ended = "",
        officialSite = "",
        schedule = Schedule(
            days = listOf("Monday"),
            time = "20:00"
        ),
        rating = Rating(
            average = 8.5
        ),
        weight = 100,
        network = Network(
            country = Country(
                code = "US",
                name = "United States",
                timezone = "America/New_York"
            ),
            id = 1,
            name = "Fake Network",
            officialSite = null
        ),
        webChannel = null,
        dvdCountry = null,
        externals = Externals(
            imdb = "tt0000000",
            thetvdb = 1,
            tvrage = 1
        ),
        image = Image(
            medium = "",
            original = ""
        ),
        summary = "<p>Fake summary</p>",
        updated = 0,
        _links = Links(
            previousepisode = PreviousEpisode(
                href = "",
                name = ""
            ),
            self = Self(
                href = ""
            )
        )
    )
}