package com.lazzy.ikimart.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lazzy.ikimart.R
import com.lazzy.ikimart.ui.theme.IkiMartTheme
import com.lazzy.ikimart.ui.theme.Shapes

@Composable
fun ItemComponent(
    image: Int,
    name: String,
    price: Int,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit
){
    Column(
        modifier = modifier
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 170.dp, height = 250.dp)
                    .clip(Shapes.medium)
            )
            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .border(
                        border = BorderStroke(2.dp, Color.LightGray),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorite", tint = MaterialTheme.colorScheme.primary)
            }
        }
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = "Rp. $price",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        CartButton(
            text = "Keranjang",
            onClick = {

            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ItemComponentPreview(){
    IkiMartTheme {
        ItemComponent(image = R.drawable.item_4, name = "Coca cola", price = 4000, onFavoriteClick = {})
    }
}