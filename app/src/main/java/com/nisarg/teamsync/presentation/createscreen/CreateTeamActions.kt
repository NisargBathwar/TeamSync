package com.nisarg.teamsync.presentation.createscreen

sealed class CreateTeamActions {
    data class ChangeTeamName(val name : String) : CreateTeamActions()
    data class CreateTeam(val name : String) : CreateTeamActions()
}