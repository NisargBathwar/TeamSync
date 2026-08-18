package com.nisarg.teamsync.presentation.loginscreen

sealed class LoginEvents {
    object LoginToHome : LoginEvents()
    object LogintToRegister : LoginEvents()
}