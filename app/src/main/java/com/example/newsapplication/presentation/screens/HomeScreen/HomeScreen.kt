package com.example.newsapplication.presentation.screens.HomeScreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
fun NewsItem(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val state = viewModel.state.value
    Log.d("NewsItem", "Sources: ${state.sources}")

    Column(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (state.error != null) {
            Text(
                text = "Error: ${state.error}",
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else if (state.sources.isNotEmpty()) {
            LazyTabRow(
                sourceList = state.sources,
                onSourceSelected = { selectedSource ->
                }
            )
        }
    }
}


