package com.nisarg.teamsync.domain.model

data class User(
    val email : String ,
    val uid : String ,
    val name : String,
    val teamIds : List<String> = emptyList()
)

