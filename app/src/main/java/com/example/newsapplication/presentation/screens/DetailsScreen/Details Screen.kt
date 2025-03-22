package com.example.newsapplication.presentation.screens.DetailsScreen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.newsapplication.R
import com.example.newsapplication.domain.model.ArticleModel
import com.example.newsapplication.presentation.screens.componant.DrawerSheet
import com.example.newsapplication.presentation.screens.componant.TopToolBar
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(modifier: Modifier = Modifier , navController: NavController , article: ArticleModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(drawerState , navController)
        }
        , modifier = Modifier.fillMaxSize()

    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopToolBar(
                    text = article.sourceName,
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        ) { innerPadding ->
            DetailsItem(modifier = Modifier.padding(innerPadding) , article = article)


        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsItem(modifier: Modifier = Modifier, article: ArticleModel) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.Crop
            )
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        GlideImage(
            model = article.urlToImage,
            contentDescription = article.title,
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = article.sourceName ?: "Unknown Author",
            modifier = Modifier.padding(10.dp),
            color = Color.Gray
        )
        Text(
            text = article.title ?: "No Title",
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = article.publishedAt ?: "No Date",
            modifier = Modifier.padding(8.dp).align(Alignment.End),
            color = Color.Gray
        )
        Text(
            text = article.description ?: "No Description",
            modifier = Modifier.padding(14.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth().align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Text(text = "Read Full Article" , color = Color.Black ,
                modifier = Modifier.padding(8.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun shape() {
    val article = ArticleModel(
        title = "ahmed"
        , description = "edgfgsdfgefdrhgrthjrtjhrtjtyj"
        , urlToImage = "https://www.google.com/imgres?q=image&imgurl=https%3A%2F%2Fimages.unsplash.com%2Fphoto-1575936123452-b67c3203c357%3Ffm%3Djpg%26q%3D60%26w%3D3000%26ixlib%3Drb-4.0.3%26ixid%3DM3wxMjA3fDB8MHxzZWFyY2h8Mnx8aW1hZ2V8ZW58MHx8MHx8fDA%253D&imgrefurl=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fimage&docid=ExDvm63D_wCvSM&tbnid=-mNI5DBCB_iEPM&vet=12ahUKEwistqj23Z2MAxXnKvsDHa_TNfUQM3oECGsQAA..i&w=3000&h=2000&hcb=2&itg=1&ved=2ahUKEwistqj23Z2MAxXnKvsDHa_TNfUQM3oECGsQAA"
        , publishedAt = "13/8/22052"
        , sourceName = "bbs"
        , url = "https://elzero.org/"
    )
    DetailsScreen(article = article , navController = NavController(LocalContext.current))

}

