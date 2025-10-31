package com.example.wear_os_test.presentation.navigation

// Routen Argumente
object NavArguments {
    const val Item_ID = "itemID"
}

// Bildschrime und Basisrouten
sealed class Screen(val route: String) {
    data object Menu : Screen("menu")

    data object  Detail : Screen("detail/{${NavArguments.Item_ID}}") {
        fun createRoute(itemID: String) = "detail/$itemID"
    }
}
