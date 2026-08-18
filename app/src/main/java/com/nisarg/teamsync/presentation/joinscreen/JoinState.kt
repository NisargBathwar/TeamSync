package com.nisarg.teamsync.presentation.joinscreen

data class JoinState (
    val isLoading : Boolean = false ,
    val error : String? = null ,
    val inviteCode : String = ""
)