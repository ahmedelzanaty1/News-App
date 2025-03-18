package com.example.newsapplication.presentation.screens.categoryscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoriesScreen(onCategorySelected: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(categorylist) { category ->
            CategoryItem(category){selectedCategory ->
                onCategorySelected(selectedCategory)

            }
        }
    }
}

@Composable
fun CategoryItem(category: CategoriesData , onCategoryClick: (String) -> Unit) {
    Box(
        modifier = Modifier.padding(7.dp)
            .fillMaxWidth(0.5f)
            .aspectRatio(1f)
            .background(category.color , shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
            .clickable {
                onCategoryClick(category.title)
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = category.image),
                contentDescription = category.title,
                modifier = Modifier
                    .size(85.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = category.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


