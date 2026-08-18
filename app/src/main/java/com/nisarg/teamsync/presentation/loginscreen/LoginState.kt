package com.nisarg.teamsync.presentation.loginscreen

data class LoginState(
    val isLoading : Boolean = false ,
    val email : String = "" ,
    val password : String = "" ,
    val error : String? = null
)
