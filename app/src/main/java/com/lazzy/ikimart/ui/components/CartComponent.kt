package com.lazzy.ikimart.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lazzy.ikimart.R
import com.lazzy.ikimart.ui.theme.IkiMartTheme
import com.lazzy.ikimart.ui.theme.Shapes

@Composable
fun MyCartComponent(
    itemId: Long,
    image: Int,
    name: String,
    price: Int,
    qty: Int,
    onItemCountChanged: (id: Long, qty: Int) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = name,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = "Rp. $price",

            )
        }

        CounterComponent(
            modifier = Modifier.padding(end = 16.dp),
            orderId = itemId,
            orderQty = qty,
            onItemIncreased = { onItemCountChanged(itemId, qty + 1) },
            onItemDecreased = { onItemCountChanged(itemId, qty - 1) })
    }

}


@Composable
@Preview(showBackground = true)
fun CartComponentPreview(){
    IkiMartTheme {
        MyCartComponent(
            itemId = 4,
            image = R.drawable.item_4,
            name = "Coca Cola can",
            price = 40000,
            qty = 2,
            onItemCountChanged = {itemId, count ->}
        )
    }
}