package com.nisarg.teamsync.presentation.homescreen

sealed class HomeEvents {
    object HomeToLogin : HomeEvents()
    object HomeToCreateOrJoin : HomeEvents()
    data class HomeToDetail(val teamId : String) : HomeEvents()
    data class HomeToTask(val teamId : String) : HomeEvents()
}