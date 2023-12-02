package com.lazzy.ikimart.di

import com.lazzy.ikimart.data.ItemRepository

object Injection {
    fun providedRepository(): ItemRepository{
        return ItemRepository.getInstance()
    }
}