package com.nisarg.teamsync.presentation.homescreen

sealed class HomeActions {
    object LogOut : HomeActions()
    object CreateOrJoin : HomeActions()
    data class TeamClicked(val teamId : String) : HomeActions()
    object Task : HomeActions()
}