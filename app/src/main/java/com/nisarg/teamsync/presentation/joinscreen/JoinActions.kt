package com.nisarg.teamsync.presentation.joinscreen

sealed class JoinActions {
    data class InviteCodeChanged(val code : String) : JoinActions()
    data class JoinTeam(val code : String) : JoinActions()
}