package com.nisarg.teamsync.presentation.teamdetail

import com.nisarg.teamsync.domain.model.TeamDetail
import com.nisarg.teamsync.domain.model.Task

data class TeamState (
    val isLoading : Boolean=  false ,
    val team : TeamDetail? = null ,
    val tasks : List<Task> = emptyList() ,
    val error : String? = null
)