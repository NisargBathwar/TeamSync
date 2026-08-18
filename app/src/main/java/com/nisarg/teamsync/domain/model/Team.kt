package com.nisarg.teamsync.domain.model

data class Team(
    val id : String ,
    val name : String ,
    val ownerId : String ,
    val inviteCode : String ,
    val members : List<String>
)
