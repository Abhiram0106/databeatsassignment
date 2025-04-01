package com.abhiram.databeatassessment.core.presentation.data_beat_assessment_app

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abhiram.databeatassessment.core.navigation.AppNavHost
import com.abhiram.databeatassessment.core.presentation.theme.AppTheme
import databeatassessment.composeapp.generated.resources.Res
import databeatassessment.composeapp.generated.resources.navigate_to_x
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource

@Composable
@Preview
fun App(
    appState: FishoAppState = rememberFishoAppState(),
) {

    val snackBarHostState = remember { SnackbarHostState() }

    AppTheme(darkTheme = isSystemInDarkTheme()) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            bottomBar = {
                val paddingValues = WindowInsets.navigationBars.asPaddingValues()
                if (appState.showBottomBar()) {
                    BottomNavigation(
                        backgroundColor = MaterialTheme.colors.background,
                        contentColor = MaterialTheme.colors.onBackground,
                        elevation = 0.dp,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        appState.topLevelDestinations.forEach { topLevelDestination ->
                            val isSelected =
                                appState.isBottomNavItemSelected(topLevelDestination.baseRoute)
                            BottomNavigationItem(
                                selected = isSelected,
                                onClick = {
                                    appState.navigateToTopLevelDestination(topLevelDestination)
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(topLevelDestination.icon),
                                        contentDescription = stringResource(
                                            Res.string.navigate_to_x,
                                            topLevelDestination.displayText
                                        ),
                                    )
                                },
                                selectedContentColor = MaterialTheme.colors.secondary,
                                unselectedContentColor = MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            },
            contentWindowInsets = WindowInsets.safeDrawing
        ) { paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .padding(paddingValues)
            ) {
                AppNavHost(
                    navHostController = appState.navController,
                    onShowSnackBar = { message, actionLabel ->
                        snackBarHostState.showSnackbar(
                            message = message.asStringNonComposable(),
                            actionLabel = actionLabel?.asStringNonComposable(),
                        ) == SnackbarResult.ActionPerformed
                    }
                )
            }
        }
    }
}