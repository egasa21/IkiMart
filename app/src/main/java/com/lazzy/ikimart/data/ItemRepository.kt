package com.lazzy.ikimart.data

import com.lazzy.ikimart.model.FakeMartDataSource
import com.lazzy.ikimart.model.ItemFavorite
import com.lazzy.ikimart.model.ItemOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ItemRepository {
    private val itemOrders = mutableListOf<ItemOrder>()

    init {
        if (itemOrders.isEmpty()){
            FakeMartDataSource.dummyItems.forEach{
                itemOrders.add(ItemOrder(it, 0))
            }
        }
    }

    fun getAllItems(): Flow<List<ItemOrder>>{
        return flowOf(itemOrders)
    }

    fun getItemById(itemId: Long) : ItemOrder{
        return itemOrders.first{
            it.item.id == itemId
        }
    }

    fun updateItemOrder(itemId: Long, newQtyValue: Int) : Flow<Boolean>{
        val index = itemOrders.indexOfFirst { it.item.id == itemId }
        val resul = if (index >= 0){
            val itemOrder = itemOrders[index]
            itemOrders[index] = itemOrder.copy(item =  itemOrder.item, qty = newQtyValue)
            true
        }else {
            false
        }
        return flowOf(resul)
    }

    fun getAddedItemOrder(): Flow<List<ItemOrder>>{
        return getAllItems()
            .map { itemOrders ->
                itemOrders.filter { itemOrder ->
                    itemOrder.qty != 0
                }
            }
    }

    fun getFavoriteItems(): Flow<List<ItemOrder>>{
        return getAllItems()
            .map { it.filter { itemOrder -> itemOrder.isFavorite }}
    }

    fun updateFavoriteItem(itemId: Long): Flow<Boolean>{
        val index = itemOrders.indexOfFirst { it.item.id == itemId }
        val result = if (index >=0){
//            val itemOrder = itemOrders[index]
            val isFavorite = !itemOrders[index].isFavorite
            itemOrders[index] = itemOrders[index].copy(isFavorite = isFavorite)
            true
        }else{
            false
        }
        return flowOf(result)
    }

    fun removeItemFromFavorite(itemId: Long): Flow<Boolean>{
        val index = itemOrders.indexOfFirst { it.item.id == itemId }
        val result = if (index >=0){
//            val itemOrder = itemOrders[index]
//            val isFavorite = !itemOrders[index].isFavorite
            val updateItem = itemOrders[index].copy(isFavorite = false)
            itemOrders[index] = updateItem
            flowOf(true)
            true
        }else{
            false
        }
        return flowOf(result)
    }

    companion object{
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(): ItemRepository =
            instance ?: synchronized(this){
               ItemRepository().apply {
                   instance = this
               }
            }
    }
}