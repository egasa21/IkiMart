package com.lazzy.ikimart.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazzy.ikimart.data.ItemRepository
import com.lazzy.ikimart.model.Item
import com.lazzy.ikimart.model.ItemOrder
import com.lazzy.ikimart.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<ItemOrder>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ItemOrder>> get() = _uiState

    fun getItemById(itemId: Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getItemById(itemId))
        }
    }

    fun addToCart(item: Item, qty: Int){
        viewModelScope.launch {
            repository.updateItemOrder(item.id, qty)
        }
    }
}