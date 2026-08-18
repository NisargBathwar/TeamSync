package com.nisarg.teamsync.presentation.register

data class RegisterState (
    val isLoading : Boolean = false ,
    val error : String? = null ,
    val email : String = "" ,
    val password : String = ""
)