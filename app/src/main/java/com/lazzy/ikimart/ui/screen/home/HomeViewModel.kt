package com.lazzy.ikimart.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazzy.ikimart.data.ItemRepository
import com.lazzy.ikimart.model.ItemOrder
import com.lazzy.ikimart.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ItemRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<ItemOrder>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ItemOrder>>>
        get() = _uiState

    fun getAllItems(){
        viewModelScope.launch {
            repository.getAllItems()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    itemOrders ->
                    _uiState.value = UiState.Success(itemOrders)
                }
        }
    }

    fun addItemToFavorite(itemId: Long){
        viewModelScope.launch {
            repository.updateFavoriteItem(itemId).collect{isUpdated ->
                if (isUpdated){
                    getAllItems()
                }
            }
        }
    }
}