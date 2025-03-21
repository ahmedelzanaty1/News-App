package com.example.newsapplication.presentation.screens.HomeScreen.componant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.newsapplication.R
import com.example.newsapplication.domain.model.ArticleModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: ArticleModel) {
    Box (modifier = Modifier.fillMaxSize()){
        Column (modifier = Modifier.fillMaxWidth().padding(8.dp)){
            GlideImage(
                model = article.urlToImage,
                contentDescription = article.title,
                modifier = Modifier.fillMaxWidth()
                    .height(250.dp)
                , loading = placeholder(R.drawable.logo)
            )
            Text(
                text = article.sourceName ?: "Unknown Author",
                modifier = Modifier.padding(top = 8.dp)
                , color = Color.Gray
            )
            Text(
                text = article.title ?: "No Title",
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = article.publishedAt ?: "No Date",
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )
            Divider(color = Color.Gray, thickness = 2.dp)
        }
    }

}