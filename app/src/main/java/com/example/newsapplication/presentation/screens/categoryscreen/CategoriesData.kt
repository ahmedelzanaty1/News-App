package com.example.newsapplication.presentation.screens.categoryscreen

import androidx.compose.ui.graphics.Color
import com.example.newsapplication.R
import com.example.newsapplication.presentation.theme.babyblue
import com.example.newsapplication.presentation.theme.blue
import com.example.newsapplication.presentation.theme.brown
import com.example.newsapplication.presentation.theme.purple
import com.example.newsapplication.presentation.theme.red
import com.example.newsapplication.presentation.theme.yellow

data class CategoriesData(
    val title: String
    , val image: Int,
    val color: Color
)
val categorylist = listOf(
    CategoriesData(
        title = "Sports",
        image = R.drawable.sports,
        color = red
    ),
            CategoriesData(
            title = "Politics",
    image = R.drawable.politics,
    color = blue
),
    CategoriesData(
        title = "Health",
        image = R.drawable.health,
        color = purple
    ),CategoriesData(
        title = "Business ",
        image = R.drawable.bussines,
        color = brown
    ),CategoriesData(
        title = "Enviroment",
        image = R.drawable.environment,
        color = babyblue
    ),CategoriesData(
        title = "Science",
        image = R.drawable.science,
        color = yellow
    )
)
