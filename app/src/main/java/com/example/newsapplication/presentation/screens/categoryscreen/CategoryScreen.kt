import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapplication.R
import com.example.newsapplication.presentation.screens.categoryscreen.CategoriesItem
import com.example.newsapplication.presentation.screens.componant.DrawerItem
import com.example.newsapplication.presentation.screens.componant.DrawerSheet
import com.example.newsapplication.presentation.screens.componant.TopToolBar
import com.example.newsapplication.presentation.theme.green
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(modifier: Modifier = Modifier , navController: NavController) {
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
                    text = "Category",
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        ) { innerPadding ->
            CategoriesItem(modifier = Modifier.padding(innerPadding))

        }
    }
}

@Preview
@Composable
private fun show() {
    CategoryScreen(navController = NavController(LocalContext.current))

}


