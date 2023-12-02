package com.lazzy.ikimart.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lazzy.ikimart.di.Injection
import com.lazzy.ikimart.model.ItemOrder
import com.lazzy.ikimart.ui.ViewModelFactory
import com.lazzy.ikimart.ui.common.UiState
import com.lazzy.ikimart.ui.components.ItemComponent


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.providedRepository())
    ),
    navigateToDetail: (Long) -> Unit,
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllItems()
            }
            is UiState.Success -> {
                HomeContent(
                    itemOrder = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    viewModel = viewModel
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    itemOrder: List<ItemOrder>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel : HomeViewModel
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ){
        items(itemOrder) { data ->
            ItemComponent(
                image = data.item.image,
                name = data.item.name,
                price = data.item.price,
                modifier = Modifier
                    .clickable {
                        navigateToDetail(data.item.id)

                    },
                onFavoriteClick = {
                    viewModel.addItemToFavorite(data.item.id)
                    Log.d("Pindah", "Oyy")
                }
            )
        }
    }
}
