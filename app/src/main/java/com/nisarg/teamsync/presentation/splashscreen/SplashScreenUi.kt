package com.nisarg.teamsync.presentation.splashscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nisarg.teamsync.presentation.navigation.NavRoutes

@Composable
fun SplashScreenUi(navController: NavController , vm : SplashScreenVm = hiltViewModel()) {

    LaunchedEffect(Unit) {
        vm.checker()
        vm.events.collect { events ->
            when(events){
                is SplashScreenEvents.NavigateToHome -> {
                    navController.navigate(NavRoutes.Home.route){
                        popUpTo(NavRoutes.Splash.route){
                            inclusive = true
                        }
                    }
                }
                is SplashScreenEvents.NavigateToLogin -> {
                    navController.navigate(NavRoutes.Login.route){
                        popUpTo(NavRoutes.Splash.route){
                            inclusive = true
                        }
                    }
                }

                is SplashScreenEvents.NavigateToTeamSetup -> {
                    navController.navigate(NavRoutes.CreateOrJoin.route){
                        popUpTo(NavRoutes.Splash.route){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Sync,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(24.dp))

            Text(
                text = "Team Sync",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Sync, Collaborate, Achieve",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(48.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                strokeWidth = 3.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Developed by Nisarg",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
            Text(
                text = "Version 1.0",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
            )
        }
    }
}
