package com.example.newsapplication.presentation.screens.categoryscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsapplication.presentation.screens.componant.TopToolBar

@Composable
fun CategoryScreen(modifier: Modifier = Modifier) {
Scaffold (modifier = Modifier.fillMaxSize() , topBar = {
    TopToolBar(text = "Category" , modifier = Modifier , onClick = {
    })
}) { innerPadding ->


}
}