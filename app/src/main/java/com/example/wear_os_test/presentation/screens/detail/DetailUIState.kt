package com.example.wear_os_test.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wear_os_test.presentation.navigation.NavArguments
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailUIState(
    val selectedItem: String = "Lade..."
)

class DetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUIState())
    val uiState = _uiState.asStateFlow()

    private val itemId: String = savedStateHandle[NavArguments.Item_ID] ?: "Fehler"
    init {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedItem = itemId) }
        }
    }
}
