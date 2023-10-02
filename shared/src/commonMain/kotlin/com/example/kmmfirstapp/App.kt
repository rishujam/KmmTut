package com.example.kmmfirstapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

/*
 * Created by Sudhanshu Kumar on 02/10/23.
 */

data class BottomNavigationItem(
    val title: String,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@Composable
fun App() {
    val bottomNavItem = listOf(
        BottomNavigationItem(
            title = "Habits",
            selectedItem = Icons.Filled.Home,
            unselectedItem = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Charts",
            selectedItem = Icons.Filled.Menu,
            unselectedItem = Icons.Outlined.Menu,
            hasNews = false,
            badgeCount = 10
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedItem = Icons.Filled.Settings,
            unselectedItem = Icons.Outlined.Settings,
            hasNews = true
        )
    )
    var count by remember {
        mutableStateOf(0)
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var selectedItem by rememberSaveable {
            mutableStateOf(0)
        }
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItem.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                            },
//                            label = {
//                                Text(text = item.title)
//                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if(item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if(item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if(index == selectedItem) {
                                            item.selectedItem
                                        } else {
                                            item.unselectedItem
                                        },
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) { }
        Button(
            onClick = {
                count++
            }
        ) {
            Text("Count: $count")
        }
    }
} 