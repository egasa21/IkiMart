package com.lazzy.ikimart.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lazzy.ikimart.R
import com.lazzy.ikimart.ui.theme.IkiMartTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
){
    ProfileContent()
}

@Composable
fun ProfileContent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
            id = R.drawable.img_profile),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .clip(shape = RoundedCornerShape(300.dp))
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = "Ega Saputra",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(text = "esaputra168@gmail.com")
    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePreview(){
    IkiMartTheme {
        ProfileContent()
    }
}
