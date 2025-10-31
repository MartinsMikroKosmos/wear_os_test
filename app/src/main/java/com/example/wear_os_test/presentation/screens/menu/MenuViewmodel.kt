package com.example.wear_os_test.presentation.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// Zustandsobjekt für die UI
data class MenuUiState(
    val items: List<String> = emptyList(),
)

// Events, die die UI auslösen kann
sealed class MenuUiEvent {
    data class OnItemClicked(val itemId: String) : MenuUiEvent()
}

// Events, die das ViewModel an die Ui sendet
sealed class NavigationEvent {
    data class NavigateToDetails(val itemId: String) : NavigationEvent()
}

class MenuViewmodel : ViewModel() {

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState = _uiState.asStateFlow()

    // Sharedflow ist ideal für "Einmal"-Events wie Navigation
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvents = _navigationEvent.asSharedFlow()

    init {
        // Daten laden TODO(noch Hardcoded)
        loadMenuItems()
    }

    private fun loadMenuItems() {
        _uiState.update {
            it.copy(
                items = (1..5).map { "Eintrag Nr. $it" })
        }
    }

    // Wird von der Ui aufgerufen
    fun onEvent(event: MenuUiEvent) {
        when (event) {
            is MenuUiEvent.OnItemClicked -> {
                // TODO: Logik hier

                // Navigation Event auslösen
                viewModelScope.launch {
                    _navigationEvent.emit(NavigationEvent.NavigateToDetails(event.itemId))
                }
            }
        }
    }
}
