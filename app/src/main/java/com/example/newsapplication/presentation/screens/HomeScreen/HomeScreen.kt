package com.example.newsapplication.presentation.screens.HomeScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsapplication.presentation.screens.componant.DrawerSheet
import com.example.newsapplication.presentation.screens.componant.HomeTopToolBar
import com.example.newsapplication.presentation.screens.componant.TopToolBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, categoryName: String) {
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
                HomeTopToolBar(
                    text = categoryName,
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onSearchClick = {
                        // Handle search icon click
                    }
                )
            }
        ) { innerPadding ->

        }
    }
}
