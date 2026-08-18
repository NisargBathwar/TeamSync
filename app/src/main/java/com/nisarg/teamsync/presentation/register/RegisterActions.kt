package com.nisarg.teamsync.presentation.register

sealed class RegisterActions{
    data class EmailChanged(val email : String ) : RegisterActions()
    data class PasswordChanged(val password : String) : RegisterActions()
    object Register : RegisterActions()
}

