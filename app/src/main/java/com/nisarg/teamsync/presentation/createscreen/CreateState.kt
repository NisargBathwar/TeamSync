package com.nisarg.teamsync.presentation.createscreen

data class CreateState(
    val isLoading : Boolean = false,
    val error : String? = null ,
    val teamName : String = ""
)
