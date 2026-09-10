package com.example.mamikostvapp.ui.screen.show_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mamikostvapp.data.DataResult
import com.example.mamikostvapp.data.model.Show
import com.example.mamikostvapp.data.repository.ShowRepository
import com.example.mamikostvapp.data.repository.ShowRepositoryInterface
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowListViewmodel @Inject constructor(
    private val repository: ShowRepositoryInterface
) : ViewModel() {
    private val _shows = MutableStateFlow<UiState<List<Show>>>(UiState.Idle)
    val shows = _shows

    //don't hardcode page = 0
    //improvement: use pagination
//    fun loadShows() {
//        _shows.value = UiState.Loading
//        viewModelScope.launch(Dispatchers.IO) {
//            when (val result = repository.getShows(0)) {
//                is DataResult.Success -> {
//                    _shows.value = UiState.Success(result.data)
//                }
//
//                is DataResult.Error -> {
//                    val error = result.error
//                    _shows.value = UiState.Error(error)
//                }
//
//                is DataResult.Empty -> _shows.value = UiState.Empty
//            }
//        }
//    }
    fun loadShows() {
        if (_shows.value is UiState.Success) return
        fetchShows()
    }

    fun refreshShows() {
        fetchShows()
    }

    private fun fetchShows() {
        _shows.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getShows(0)) {
                is DataResult.Success -> {
                    _shows.value = UiState.Success(result.data)
                }

                is DataResult.Error -> {
                    _shows.value = UiState.Error(result.error)
                }

                is DataResult.Empty -> {
                    _shows.value = UiState.Empty
                }
            }
        }
    }
}