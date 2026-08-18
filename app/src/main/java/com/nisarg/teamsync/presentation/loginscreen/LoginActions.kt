package com.nisarg.teamsync.presentation.loginscreen

sealed class LoginActions {
    data class EmailChanged(val email : String ) : LoginActions()
    data class PasswordChanged(val password : String) : LoginActions()
    object Login : LoginActions()
    object Register : LoginActions()
}