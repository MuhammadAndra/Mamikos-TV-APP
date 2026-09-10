package com.example.mamikostvapp.ui.screen.show_detail

import android.content.Context
import android.content.Intent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mamikostvapp.data.DataResult
import com.example.mamikostvapp.data.model.Show
import com.example.mamikostvapp.data.repository.ShowRepository
import com.example.mamikostvapp.data.repository.ShowRepositoryInterface
import com.example.mamikostvapp.navigation.nav_graph.ShowDetail
import com.example.saferecycle.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewmodel @Inject constructor(
    private val repository: ShowRepositoryInterface,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _showDetail = MutableStateFlow<UiState<Show>>(UiState.Idle)
    val showDetail = _showDetail
    private val args = ShowDetail.from(savedStateHandle)

    fun loadShowDetail() {
        _showDetail.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getShowDetail(args.id)) {
                is DataResult.Success -> {
                    _showDetail.value = UiState.Success(result.data)
                }

                is DataResult.Error -> {
                    val error = result.error
                    _showDetail.value = UiState.Error(error)
                }

                is DataResult.Empty -> _showDetail.value = UiState.Empty
            }

        }
    }

    fun shareShow(context: Context, show: Show) {
        val shareText = """
        ${show.name}
        
        ${show.summary.cleanHtml()}
        
        ${show.url}
    """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
        }

        context.startActivity(
            Intent.createChooser(intent, "Share TV Show")
        )
    }

}