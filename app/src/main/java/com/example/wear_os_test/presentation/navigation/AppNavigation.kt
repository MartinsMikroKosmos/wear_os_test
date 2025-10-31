package com.example.wear_os_test.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.wear_os_test.presentation.screens.detail.DetailScreen
import com.example.wear_os_test.presentation.screens.menu.MenuScreen
import com.example.wear_os_test.presentation.screens.menu.MenuViewmodel
import com.example.wear_os_test.presentation.screens.menu.NavigationEvent

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Screen.Menu.route
    ) {
        composable(Screen.Menu.route) {
            val viewmodel: MenuViewmodel = viewModel()

            MenuScreen(
                viewmodel = viewmodel,
                navigationEvents = viewmodel.navigationEvents,
                onNavigate = { event ->
                    when (event) {
                        is NavigationEvent.NavigateToDetails -> {
                            navController.navigate(Screen.Detail.createRoute(event.itemId))
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(NavArguments.Item_ID) {type = NavType.StringType})
        ) {
            DetailScreen()
        }
    }
}