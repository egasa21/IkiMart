package com.lazzy.ikimart.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lazzy.ikimart.data.ItemRepository
import com.lazzy.ikimart.ui.screen.cart.CartViewModel
import com.lazzy.ikimart.ui.screen.detail.DetailViewModel
import com.lazzy.ikimart.ui.screen.favorite.FavoriteViewModel
import com.lazzy.ikimart.ui.screen.home.HomeViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: ItemRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}