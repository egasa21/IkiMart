package com.lazzy.ikimart.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import com.lazzy.ikimart.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lazzy.ikimart.di.Injection
import com.lazzy.ikimart.ui.ViewModelFactory
import com.lazzy.ikimart.ui.common.UiState
import com.lazzy.ikimart.ui.components.CheckoutButton
import com.lazzy.ikimart.ui.components.MyCartComponent

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.providedRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderItems()
            }
            is UiState.Success -> {
                CartContent(
                    state = uiState.data,
                    onItemCountChanged = { itemdId, qty ->
                        viewModel.updateOrderItem(itemdId, qty)
                    }, onItemButtonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: CartState,
    onItemCountChanged: (id: Long, count: Int) -> Unit,
    onItemButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            items(state.itemOrder, key = {it.item.id}){ item ->
                MyCartComponent(
                    itemId = item.item.id,
                    image = item.item.image,
                    name = item.item.name,
                    price = item.item.price * item.qty,
                    qty = item.qty,
                    onItemCountChanged = onItemCountChanged
                )
                Divider()
            }
        }
        CheckoutButton(
            text = "Checkout",
            enabled = state.itemOrder.isNotEmpty(),
            onClick = {
                onItemButtonClicked("Item checkout Successfully")
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}