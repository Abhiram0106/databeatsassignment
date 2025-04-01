package com.abhiram.databeatassessment.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.abhiram.databeatassessment.core.navigation.nav_graphs.homeGraph
import com.abhiram.databeatassessment.core.util.UiText

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    NavHost(
        navController = navHostController,
        startDestination = Graphs.Home,
        enterTransition = {
            fadeIn(animationSpec = tween(200)).plus(
                slideInHorizontally(
                    animationSpec = tween(200),
                    initialOffsetX = { fullWidth ->
                        fullWidth / 5
                    }
                )
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(200)).plus(
                slideOutHorizontally(
                    animationSpec = tween(200),
                    targetOffsetX = { fullWidth ->
                        -fullWidth / 5
                    }
                )
            )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(200)).plus(
                slideInHorizontally(
                    animationSpec = tween(200),
                    initialOffsetX = { fullWidth ->
                        -fullWidth / 5
                    }
                )
            )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(200)).plus(
                slideOutHorizontally(
                    animationSpec = tween(200),
                    targetOffsetX = { fullWidth ->
                        fullWidth / 5
                    }
                )
            )
        }
    ) {

        homeGraph(
            navController = navHostController,
            onShowSnackBar = onShowSnackBar
        )
    }
}