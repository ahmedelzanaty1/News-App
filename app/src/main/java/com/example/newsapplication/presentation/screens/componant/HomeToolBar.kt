package com.example.newsapplication.presentation.screens.componant

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapplication.R
import com.example.newsapplication.presentation.theme.green


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopToolBar(
    text: String,
    onClick: () -> Unit,
    onSearchClick: () -> Unit
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
                        modifier = Modifier.padding(10.dp).size(50.dp),
                        contentScale = ContentScale.Fit,
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu Icon"

                    )
                }
            },
            actions = {
            IconButton(
                onClick = onSearchClick
            ) {
                Image(
                    modifier = Modifier.padding(10.dp).size(50.dp),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.search_ic),
                    contentDescription = "Search Icon"
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


@Preview(showBackground = true , showSystemUi = true)
@Composable
private fun shape() {
    HomeTopToolBar(
        text = "Home",
        onClick = { /*TODO*/ },
        onSearchClick = { /*TODO*/ })

}