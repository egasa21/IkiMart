package com.lazzy.ikimart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lazzy.ikimart.ui.theme.IkiMartTheme

@Composable
fun CheckoutButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
){
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Icon(Icons.Default.ShoppingCart, contentDescription = null, Modifier.padding(end = 8.dp))
        Text(text = text, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Composable
@Preview(showBackground = true)
fun CheckoutButtonPreview() {
    IkiMartTheme {
        CheckoutButton(
            text = "Checkout",
            onClick = {}
        )
    }
}