package com.lazzy.ikimart.ui.screen.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazzy.ikimart.data.ItemRepository
import com.lazzy.ikimart.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavoriteState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavoriteState>> get() = _uiState

    fun getFavoriteItems(){
        viewModelScope.launch {
            repository.getFavoriteItems()
                .collect{ favItems ->
                    _uiState.value = UiState.Success(FavoriteState(favItems))
                    Log.d("FavoriteViewModel", "Received data: $favItems")
                }
        }
    }

    fun removeItemFromFavorites(itemId: Long){
        viewModelScope.launch {
            repository.removeItemFromFavorite(itemId).collect{ success ->
                if (success){
                    getFavoriteItems()
                }
            }
        }
    }
}