package com.a6tak.practice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.a6tak.practice.db.NewsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: NewsRepository) : ViewModel() {

    // Expose screen UI state
    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Idle)
    val state: StateFlow<State> = _state.asStateFlow()


    init {
        observeNews()
    }
    // Handle business logic
    suspend fun loadNews() {
        _state.value = State.Loading
    }

    suspend fun addNewItem(title: String) {
        val newsEntity = NewsEntity(
            title = title,
            message = "$title + loooolng loooln glooongll  ngl o gooolang"
        )
        repository.addNew(newsEntity)
    }

    suspend fun resetAndRestart() {
        _state.value = State.Idle
//        loadNews()
    }

    private fun observeNews() {
        viewModelScope.launch {
            repository.getNews().collect {
                _state.value = State.Data(it)
            }
        }
    }

    sealed interface State {
        data object Idle : State
        data object Loading : State
        data class Data(val list: List<NewsEntity>) : State
        data class Error(val cause: Throwable)
    }
}

class ProfileViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModelClass")
    }
}