package com.nisarg.teamsync.data.model

data class TeamDto (
    val id : String = "" ,
    val name : String = "" ,
    val ownerId : String = "",
    val inviteCode : String = "",
    val members : List<String> = emptyList()
)