package com.example.newsapplication.presentation.screens.HomeScreen.componant

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapplication.domain.model.SourceModel
import com.example.newsapplication.presentation.theme.green

@Composable
fun LazyTabRow(
    sourceList: List<SourceModel?>,
    modifier: Modifier = Modifier,
    onSourceSelected: (String) -> Unit
) {
    val selectedIndex = remember { mutableIntStateOf(0) }

    val selectedModifier = Modifier
        .padding(4.dp)
        .background(green, shape = CircleShape)
        .padding(horizontal = 8.dp, vertical = 4.dp)

    val unselectedModifier = Modifier
        .padding(4.dp)
        .border(2.dp, green.copy(alpha = 1f), shape = CircleShape)
        .padding(horizontal = 8.dp, vertical = 4.dp)

    if (sourceList.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex.intValue,
            modifier = modifier,
            divider = {},
            edgePadding = 0.dp,
            indicator = {}
        ) {
            sourceList.forEachIndexed { index, source ->
                Tab(
                    selected = selectedIndex.intValue == index,
                    onClick = {
                        if (source != null) {
                            source.name?.let { onSourceSelected(it) }
                        }
                        selectedIndex.intValue = index
                    },
                    modifier = if (selectedIndex.intValue == index) selectedModifier else unselectedModifier,
                    text = {
                        if (source != null) {
                            source.name?.let {
                                Text(
                                    text = it,
                                    color = if (selectedIndex.intValue == index) Color.White else Color.Green
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}
