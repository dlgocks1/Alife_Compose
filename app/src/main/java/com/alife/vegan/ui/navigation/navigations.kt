@file:OptIn(ExperimentalMaterialApi::class)

package com.alife.vegan.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alife.vegan.ui.Screen
import com.alife.vegan.ui.calendar.CalendarScreen
import com.alife.vegan.ui.calendar.CalendarViewModel
import com.alife.vegan.ui.home.HomeScreen
import com.alife.vegan.ui.onboard.RegisterDietBudgetScreen
import com.alife.vegan.ui.onboard.RegisterDietFoodScreen
import com.alife.vegan.ui.setting.*
import com.alife.vegan.ui.shpping.ShoppingScreen

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

fun NavGraphBuilder.registerDiet(navController: NavController) {
    navigation(startDestination = Screen.RegisterDietBudget.route, route = "RegisterDietGraph") {
        composable(Screen.RegisterDietBudget.route) {
            RegisterDietBudgetScreen(navController)
        }
        composable(Screen.RegisterDietFood.route) {
            RegisterDietFoodScreen(navController)
        }
    }
}

fun NavGraphBuilder.settingGraph(navController: NavController) {
    navigation(startDestination = Screen.SettingGender.route, route = "SettingGraph") {
        composable(Screen.SettingGender.route) {
            val settingDietViewModel: SettingDietViewModel =
                GenerateSettingViewModel(it, navController)
            SettingGenderScreen(navController, settingDietViewModel)
        }
        composable(Screen.SettingExercise.route) {
            val settingDietViewModel: SettingDietViewModel =
                GenerateSettingViewModel(it, navController)
            SettingExerciseScreen(navController, settingDietViewModel)
        }
        composable(Screen.SettingFoodCategory.route) {
            val settingDietViewModel: SettingDietViewModel =
                GenerateSettingViewModel(it, navController)
            SettingFoodCategoryScreen(navController, settingDietViewModel)
        }
        composable(Screen.SettingAllergy.route) {
            val settingDietViewModel: SettingDietViewModel =
                GenerateSettingViewModel(it, navController)
            SettingAllergy(navController, settingDietViewModel)
        }
        composable(Screen.SettingVegunCategory.route) {
            val settingDietViewModel: SettingDietViewModel =
                GenerateSettingViewModel(it, navController)
            SettingVegunCategoryScreen(navController, settingDietViewModel)
        }
        composable(Screen.SettingDietDirection.route) {
            val settingDietViewModel: SettingDietViewModel =
                GenerateSettingViewModel(it, navController)
            SettingDietDirectionScreen(navController, settingDietViewModel)
        }
    }
}


@Composable
private fun GenerateSettingViewModel(
    it: NavBackStackEntry,
    navController: NavController
): SettingDietViewModel {
    val backStackEntry = remember(it) {
        navController.getBackStackEntry("SettingGraph")
    }
    return hiltViewModel(backStackEntry)
}
