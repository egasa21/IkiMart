package com.lazzy.ikimart.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lazzy.ikimart.ui.theme.IkiMartTheme

@Composable
fun CounterComponent(
    orderId: Long,
    orderQty: Int,
    onItemIncreased: (Long) -> Unit,
    onItemDecreased: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 110.dp, height = 40.dp)
            .padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 6.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            color = Color.Transparent,
            contentColor = Color.LightGray,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "-",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onItemDecreased(orderId)
                    }
            )
        }
        Text(
            text = orderQty.toString(),
            modifier = Modifier
                .weight(1f),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 6.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            color = Color.Transparent,
            contentColor = Color.LightGray,
            modifier = Modifier.size(30.dp)
        ) {
            Text(
                text = "+",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onItemIncreased(orderId)
                    }
            )
        }
    }
}


@Preview
@Composable
fun ProductCounterPreview() {
    IkiMartTheme {
        CounterComponent(
            orderId = 1,
            orderQty = 0,
            onItemIncreased = { },
            onItemDecreased = { }
        )
    }
}
