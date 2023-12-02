package com.lazzy.ikimart.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazzy.ikimart.data.ItemRepository
import com.lazzy.ikimart.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>> get() = _uiState

    fun getAddedOrderItems(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedItemOrder()
                .collect{ itemOrder ->
                    val totalPrice = itemOrder.sumOf { it.item.price * it.qty }
                    _uiState.value = UiState.Success(CartState(itemOrder, totalPrice))
                }
        }
    }

    fun updateOrderItem(itemId: Long, qty: Int){
        viewModelScope.launch {
            repository.updateItemOrder(itemId, qty)
                .collect{ isUpdated ->
                    if (isUpdated){
                        getAddedOrderItems()
                    }
                }
        }
    }
}