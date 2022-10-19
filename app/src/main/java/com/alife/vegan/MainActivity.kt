@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.alife.vegan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.alife.vegan.ui.Screen
import com.alife.vegan.ui.calendar.CalendarScreen
import com.alife.vegan.ui.calendar.CalendarViewModel
import com.alife.vegan.ui.home.HomeScreen
import com.alife.vegan.ui.setting.SettingAllergy
import com.alife.vegan.ui.setting.SettingFoodCategoryScreen
import com.alife.vegan.ui.setting.SettingExerciseScreen
import com.alife.vegan.ui.setting.SettingGenderScreen
import com.alife.vegan.ui.shpping.ShoppingScreen
import com.alife.vegan.ui.theme.AlifeTheme
import com.alife.vegan.ui.theme.Color_Alife_C4C4C4
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RootIndex()
        }
    }
}

@Composable
private fun RootIndex() {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        "home", "calendar", "shopping" -> {
            bottomBarState.value = true
        }
        "setting_gender", "setting_exercise", "setting_diet_direction" -> {
            bottomBarState.value = false
        }
    }

    AlifeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            RootNavhost(navController, bottomBarState)
        }
    }
}

@Composable
private fun RootNavhost(navController: NavHostController, bottomBarState: MutableState<Boolean>) {
    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Calendar,
        Screen.Shopping,
    )
    Scaffold(
        bottomBar = { ButtomBar(navController, bottomNavItems, bottomBarState) }
    ) { innerPadding ->

        val calendarViewModel: CalendarViewModel = hiltViewModel()

        NavHost(
            navController,
            startDestination = "MainGraph",
            Modifier.padding(innerPadding)
        ) {
            mainGraph(
                calendarViewModel = calendarViewModel,
                navController = navController
            )
            settingGraph(
                navController = navController
            )
        }
    }
}

@Composable
private fun ButtomBar(
    navController: NavHostController,
    bottomNavItems: List<Screen>,
    bottomBarState: MutableState<Boolean>
) {
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomNavigation(
            modifier = Modifier.background(color = Color.White),
            backgroundColor = Color.White,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            bottomNavItems.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = screen.drawableResId ?: return@BottomNavigationItem
                            ),
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(screen.stringResId)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    },
                    selectedContentColor = Color_Alife_Cyan,
                    unselectedContentColor = Color_Alife_C4C4C4
                )
            }
        }
    }
}

fun NavGraphBuilder.mainGraph(navController: NavController, calendarViewModel: CalendarViewModel) {
    navigation(startDestination = Screen.Calendar.route, route = "MainGraph") {
        composable(Screen.Calendar.route) {
            CalendarScreen(
                viewModel = calendarViewModel,
                navController = navController
            )
        }
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Shopping.route) { ShoppingScreen() }
    }
}

fun NavGraphBuilder.settingGraph(navController: NavController) {
    navigation(startDestination = Screen.SettingGender.route, route = "SettingGraph") {
        composable(Screen.SettingGender.route) { SettingGenderScreen(navController) }
        composable(Screen.SettingExercise.route) { SettingExerciseScreen(navController) }
        composable(Screen.SettingDietDirection.route) { SettingFoodCategoryScreen(navController) }
        composable(Screen.SettingAllergy.route) { SettingAllergy(navController) }
    }
}

