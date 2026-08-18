package com.nisarg.teamsync.presentation.homescreen

import com.nisarg.teamsync.domain.model.Team
import com.nisarg.teamsync.domain.model.User

data class HomeState(
    val isLoading : Boolean = false ,
    val error : String? = null ,
    val currentUser : User? = null ,
    val team : List<Team> = emptyList() ,
    val teamId : String = ""
)