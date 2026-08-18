package com.nisarg.teamsync.presentation.teamdetail

sealed class TeamActions {
    data object Back : TeamActions()
    data class Team(val teamId : String) : TeamActions()
}