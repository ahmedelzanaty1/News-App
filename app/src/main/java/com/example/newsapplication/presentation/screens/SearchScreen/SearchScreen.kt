package com.example.newsapplication.presentation.screens.SearchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapplication.R
import com.example.newsapplication.conestant.Resource
import com.example.newsapplication.data.remote.dto.toArticleModel
import com.example.newsapplication.presentation.screens.HomeScreen.componant.ArticleCard
import com.example.newsapplication.presentation.screens.SearchScreen.ViewModel.SearchViewModel
import com.example.newsapplication.presentation.screens.SearchScreen.componant.SearchTopBar
import com.example.newsapplication.presentation.screens.componant.DrawerSheet
import com.example.newsapplication.presentation.theme.green
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val searchState by viewModel.searchState.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerSheet(drawerState, navController) },
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                SearchTopBar(
                    searchQuery = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = {
                        if (searchQuery.text.isNotEmpty()) {
                            viewModel.searchArticles(searchQuery.text, 1)
                        }
                    },
                    onClear = { searchQuery = TextFieldValue("") },
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.pattern),
                        contentScale = ContentScale.Crop
                    )
                    .padding(innerPadding)
            ) {
                when (searchState) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        val articles = (searchState as Resource.Success).data?.articles ?: emptyList()
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(articles) { article ->
                                ArticleCard(article = article.toArticleModel(), navController = navController)
                            }
                        }
                    }

                    is Resource.Error -> {
                        Text(
                            text = (searchState as Resource.Error).message ?: "Unknown Error",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}
