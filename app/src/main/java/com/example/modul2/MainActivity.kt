package com.example.modul2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.modul2.ui.theme.Modul2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Modul2Theme {
                MainAppScreen()
            }
        }
    }
}

@Composable
fun MainAppScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Tiket", "Percobaan 1", "Percobaan 2", "Percobaan 3", "Profil")
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            ScrollableTabRow(
                selectedTabIndex = selectedTab,
                edgePadding = 0.dp
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> PemesananTiketScreen()
                1 -> ChangeTextScreen()
                2 -> EventStateTest()
                3 -> ImplicitIntentTest(context)
                4 -> HalamanUtama()
            }
        }
    }
}