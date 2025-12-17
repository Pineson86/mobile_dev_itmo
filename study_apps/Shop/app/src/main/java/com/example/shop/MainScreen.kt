package com.example.shop

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shop.data.Repository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarState = remember { mutableStateOf(true) }

    // Determine the current screen to manage bottom bar visibility
    val currentRoute by navController.currentBackStackEntryAsState()
    val currentRouteName = currentRoute?.destination?.route ?: ""
    bottomBarState.value = currentRouteName in listOf("catalog", "cart", "profile")

    Scaffold(
        bottomBar = {
            if (bottomBarState.value) {
                BottomNavigationBar(navController = navController, currentRoute = currentRouteName)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = if (Repository.currentUser != null) "catalog" else "login"
            ) {
                composable("login") { LoginScreen(navController) }
                composable("catalog") { CatalogScreen(navController) }
                composable("cart") { CartScreen(navController) }
                composable("profile") { ProfileScreen(navController) }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: androidx.navigation.NavController, currentRoute: String) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Главная") },
            label = { Text("Каталог") },
            selected = currentRoute == "catalog",
            onClick = {
                navController.navigate("catalog") {
                    popUpTo("catalog") { inclusive = true }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Корзина") },
            label = { Text("Корзина") },
            selected = currentRoute == "cart",
            onClick = {
                navController.navigate("cart") {
                    popUpTo("cart") { inclusive = true }
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Профиль") },
            label = { Text("Профиль") },
            selected = currentRoute == "profile",
            onClick = {
                navController.navigate("profile") {
                    popUpTo("profile") { inclusive = true }
                }
            }
        )
    }
}