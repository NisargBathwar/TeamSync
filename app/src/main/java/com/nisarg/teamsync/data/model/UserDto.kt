package com.nisarg.teamsync.data.model

data class UserDto(
    val email : String = "",
    val uid : String  = "",
    val name : String = "",
    val teamIds : List<String> = emptyList()
)

