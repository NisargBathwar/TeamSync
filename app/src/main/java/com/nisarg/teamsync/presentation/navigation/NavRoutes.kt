package com.nisarg.teamsync.presentation.navigation

sealed class NavRoutes(val route : String) {
    object Home : NavRoutes("home")
    object Login : NavRoutes("login")
    object Register : NavRoutes("register")
    object Splash : NavRoutes("splash")
    object Join : NavRoutes("join")
    object CreateOrJoin : NavRoutes("createOrjoin")
    object Create : NavRoutes("create")
    object TeamDetail : NavRoutes("detail/{teamId}")
    object Task : NavRoutes("tasks/{teamId}")
}