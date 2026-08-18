package com.nisarg.teamsync.presentation.splashscreen

sealed class SplashScreenEvents {
    object NavigateToHome : SplashScreenEvents()
    object NavigateToLogin : SplashScreenEvents()
    object NavigateToTeamSetup : SplashScreenEvents()
}
