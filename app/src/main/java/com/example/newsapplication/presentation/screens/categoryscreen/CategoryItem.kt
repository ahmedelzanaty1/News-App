package com.example.newsapplication.presentation.screens.categoryscreen

import CategoryScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapplication.R

@Composable
fun CategoriesItem(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackGround()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextComponent()
            Spacer(modifier = Modifier.height(10.dp))
            CategoriesScreen()
        }
    }
}

@Composable
fun BackGround(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.pattern),
        contentDescription = "Background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun TextComponent(modifier: Modifier = Modifier) {
    Text(
        text = "Pick your category \n of interest ",
        color = Color.Black,
        modifier = Modifier.padding(30.dp , top = 100.dp),
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}


@Preview(showBackground = true , showSystemUi = true)
@Composable
private fun Category() {
    CategoriesItem()

}