package com.android101.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android101.list.ui.ListNavigation
import com.android101.list.ui.ListScreen

@Composable
fun Android101Navigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListNavigation) {
        composable<ListNavigation> {
            ListScreen(modifier)
        }
    }
}
