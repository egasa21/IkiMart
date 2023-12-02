package com.lazzy.ikimart.ui.screen.cart

import com.lazzy.ikimart.model.ItemOrder

data class CartState(
    val itemOrder: List<ItemOrder>,
    val price: Int
)