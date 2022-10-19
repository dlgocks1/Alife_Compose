package com.alife.vegan.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alife.vegan.R

sealed class Screen(
    val route: String,
    @StringRes val stringResId: Int,
    @DrawableRes val drawableResId: Int? = null
) {
    object Calendar :
        Screen("calendar", R.string.calendar_screen, R.drawable.ic_baseline_calendar_today_24)

    object Shopping :
        Screen("shopping", R.string.shopping_screen, R.drawable.ic_baseline_shopping_cart_24)

    object Home :
        Screen("home", R.string.home_screen, R.drawable.ic_baseline_home_24)

    object SettingGender :
        Screen("setting_gender", R.string.setting_gender_screen)

    object SettingExercise :
        Screen("setting_exercise", R.string.setting_exercise_screen)

    object SettingDietDirection :
        Screen("setting_diet_direction", R.string.setting_dietdirection_screen)

    object SettingAllergy :
        Screen("setting_allergy", R.string.setting_dietdirection_screen)
}