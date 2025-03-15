package com.example.newsapplication.presentation.screens.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapplication.R
import com.example.newsapplication.conestant.Route
import com.example.newsapplication.presentation.theme.green
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolBar(
    text: String,
    onClick: () -> Unit
) {
    Surface(
        color = green,
        shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = text,
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(onClick = onClick) {
                    Image(
                        modifier = Modifier.padding(10.dp),
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu Icon"
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.White
            )
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheet(drawerState: DrawerState, navController: NavController) {
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier) {
        Box(
            modifier = Modifier
                .height(110.dp)
                .fillMaxWidth(.6f)
                .background(green),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "News App",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier.background(Color.White).fillMaxHeight()) {
            DrawerItem(
                icon = R.drawable.menu_drawer,
                text = "Category",
                onClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Route.CATEGORY)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            DrawerItem(
                icon = R.drawable.setting_drawer,
                text = "Settings",
                onClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Route.SETTING)
                }
            )
        }
    }
}

@Composable
fun DrawerItem(icon: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(.6f)
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text, fontSize = 18.sp, color = Color.Black)
    }
}





