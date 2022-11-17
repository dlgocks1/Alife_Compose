@file:OptIn(ExperimentalMaterialApi::class)

package com.alife.vegan.ui.navigation

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import com.alife.vegan.ui.Screen
import com.alife.vegan.ui.calendar.CalendarScreen
import com.alife.vegan.ui.calendar.CalendarViewModel
import com.alife.vegan.ui.detail.DetailScreen
import com.alife.vegan.ui.home.HomeScreen
import com.alife.vegan.ui.onboard.RegisterDietBudgetScreen
import com.alife.vegan.ui.registerDiet.RegisterDietFoodScreen
import com.alife.vegan.ui.onboard.RegisterShoppingCart
import com.alife.vegan.ui.registerDiet.RegisterDietViewModel
import com.alife.vegan.ui.registerDiet.RegisterRecDietFoodScreen
import com.alife.vegan.ui.setting.*
import com.alife.vegan.ui.shpping.ShoppingResultScreen
import com.alife.vegan.ui.shpping.ShoppingScreen
import com.alife.vegan.ui.shpping.ShoppingViewModel
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.mainGraph(
    navController: NavController,
    calendarViewModel: CalendarViewModel,
    shoppingViewModel: ShoppingViewModel
) {
    navigation(startDestination = Screen.Calendar.route, route = "MainGraph") {
        composable(Screen.Calendar.route) {
            CalendarScreen(
                viewModel = calendarViewModel,
                navController = navController
            )
        }
        composable(Screen.Home.route) { HomeScreen(calendarViewModel) }
        composable(Screen.Shopping.route) { ShoppingScreen(navController, shoppingViewModel) }
        composable(Screen.ShoppingResult.route) {
            ShoppingResultScreen(navController, shoppingViewModel)
        }
        composable(
            route = Screen.Detail.route
        ) { entry ->
            DetailScreen()
        }

    }
}

fun NavGraphBuilder.registerDiet(navController: NavController) {
    navigation(startDestination = Screen.RegisterDietBudget.route, route = "RegisterDietGraph") {
        composable(Screen.RegisterDietBudget.route) { entry ->
            val backStackEntry = remember(entry) {
                navController.getBackStackEntry("RegisterDietGraph")
            }
            val registerViewModel: RegisterDietViewModel = hiltViewModel(backStackEntry)
            RegisterDietBudgetScreen(navController, registerViewModel)
        }
        composable(Screen.RegisterDietFood.route) { entry ->
            val backStackEntry = remember(entry) {
                navController.getBackStackEntry("RegisterDietGraph")
            }
            val registerViewModel: RegisterDietViewModel = hiltViewModel(backStackEntry)
            RegisterDietFoodScreen(navController, registerViewModel)
        }
        composable(Screen.RegisterRecDietFood.route) { entry ->
            val backStackEntry = remember(entry) {
                navController.getBackStackEntry("RegisterDietGraph")
            }
            val registerViewModel: RegisterDietViewModel = hiltViewModel(backStackEntry)
            RegisterRecDietFoodScreen(navController, registerViewModel)
        }
        composable(Screen.RegisterDietShoppingCart.route) { entry ->
            val backStackEntry = remember(entry) {
                navController.getBackStackEntry("RegisterDietGraph")
            }
            val registerViewModel: RegisterDietViewModel = hiltViewModel(backStackEntry)
            RegisterShoppingCart(navController, registerViewModel)
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

class AssetParamType : NavType<GetFoodByPriceResponseItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): GetFoodByPriceResponseItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): GetFoodByPriceResponseItem {
        return Gson().fromJson(value, GetFoodByPriceResponseItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: GetFoodByPriceResponseItem) {
        bundle.putParcelable(key, value)
    }
}