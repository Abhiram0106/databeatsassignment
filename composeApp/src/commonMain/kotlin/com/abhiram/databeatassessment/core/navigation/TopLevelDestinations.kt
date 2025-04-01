package com.abhiram.databeatassessment.core.navigation

import databeatassessment.composeapp.generated.resources.Res
import databeatassessment.composeapp.generated.resources.home
import databeatassessment.composeapp.generated.resources.ic_home
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val icon: DrawableResource,
    val displayText: StringResource,
    val route: @Serializable KClass<*>,
    val baseRoute: @Serializable KClass<*>
) {
    Home(
        icon = Res.drawable.ic_home,
        displayText = Res.string.home,
        route = Screens.Home::class,
        baseRoute = Graphs.Home::class
    ),
}