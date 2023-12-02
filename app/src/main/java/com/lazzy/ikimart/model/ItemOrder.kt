package com.lazzy.ikimart.model

data class ItemOrder(
    val item: Item,
    val qty: Int,
    val isFavorite: Boolean = false
)