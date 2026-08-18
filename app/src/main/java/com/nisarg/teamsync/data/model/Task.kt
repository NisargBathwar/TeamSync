package com.nisarg.teamsync.data.model

data class TaskDto(
    val id : String = ""  ,
    val teamId : String = "" ,
    val title : String = "" ,
    val description : String = "" ,
    val userName : String = "" ,
    val isCompleted : Boolean = false ,
    val completedBy : List<String> = emptyList()
)
