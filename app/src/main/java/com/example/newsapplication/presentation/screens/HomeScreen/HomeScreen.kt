package com.example.newsapplication.presentation.screens.HomeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapplication.R
import com.example.newsapplication.presentation.screens.HomeScreen.componant.ArticleCard
import com.example.newsapplication.presentation.screens.HomeScreen.componant.LazyTabRow
import com.example.newsapplication.presentation.screens.componant.DrawerSheet
import com.example.newsapplication.presentation.screens.componant.HomeTopToolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, categoryName: String, viewModel: HomeViewModel = hiltViewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getSources(categoryName)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerSheet(drawerState, navController)
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HomeTopToolBar(
                    text = categoryName,
                    onClick = {
                        scope.launch { drawerState.open() }
                    },
                    onSearchClick = {
                        // Handle search icon click
                    }
                )
            }
        ) { innerPadding ->
            NewsItem(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
        }
    }
}

@Composable
fun NewsItem(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val articleState = viewModel.articleState.value
    Log.d("NewsItem", "Sources: ${state.sources}")

    Column(
        modifier = modifier.fillMaxSize().paint(
            painter = painterResource(id = R.drawable.pattern),
            contentScale = ContentScale.Crop
        )
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        when {
            state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            state.error != null -> Text(
                text = "Error: ${state.error}",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            state.sources.isNotEmpty() -> {
                LazyTabRow(
                    sourceList = state.sources,
                    onSourceSelected = { selectedSource ->
                        viewModel.getArticles(selectedSource)
                    }
                )
            }
            else -> Text(
                text = "No sources available",
                color = Color.Black,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        when {
            articleState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            articleState.error != null -> Text(
                text = "Error: ${articleState.error}",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            articleState.sources.isNotEmpty() -> {
                LazyColumn {
                    items(articleState.sources) { article ->
                        article?.let {
                            ArticleCard(article = it)
                        }
                    }
                }

            }
            else -> Image(
                painter = painterResource(id = R.drawable.not_found),
                contentDescription = "No Data",
                modifier = Modifier.height(200.dp).align(Alignment.CenterHorizontally)
                , contentScale = ContentScale.Crop
                , alignment = Alignment.Center
            )
        }
    }
}





