package com.abhiram.databeatassessment.core.navigation.nav_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abhiram.databeatassessment.core.navigation.Graphs
import com.abhiram.databeatassessment.core.navigation.Screens
import com.abhiram.databeatassessment.core.util.UiText
import com.abhiram.databeatassessment.feature_home.presentation.HomeRoot

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    navigation<Graphs.Home>(startDestination = Screens.Home) {

        composable<Screens.Home> {
            HomeRoot(

            )
        }
    }
}