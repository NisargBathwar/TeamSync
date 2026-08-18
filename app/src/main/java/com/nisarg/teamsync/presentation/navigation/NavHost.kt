package com.nisarg.teamsync.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nisarg.teamsync.presentation.createorjoin.CreateOrJoinUi
import com.nisarg.teamsync.presentation.createscreen.CreateTeamUi
import com.nisarg.teamsync.presentation.homescreen.HomeUi
import com.nisarg.teamsync.presentation.joinscreen.JoinUi
import com.nisarg.teamsync.presentation.loginscreen.LoginUi
import com.nisarg.teamsync.presentation.register.RegisterUi
import com.nisarg.teamsync.presentation.splashscreen.SplashScreenUi
import com.nisarg.teamsync.presentation.taskscreen.TaskScreen
import com.nisarg.teamsync.presentation.teamdetail.TeamDetailUi

@Composable
fun NavHost() {

    val navController = rememberNavController()

    NavHost(
        navController ,
        startDestination = NavRoutes.Splash.route
    ) {
        composable(NavRoutes.Home.route){
            HomeUi(navController)
        }

        composable(NavRoutes.Login.route){
            LoginUi(navController)
        }

        composable(NavRoutes.Splash.route){
            SplashScreenUi(navController)
        }

        composable(NavRoutes.Register.route){
            RegisterUi(navController)
        }

        composable(NavRoutes.Join.route){
            JoinUi(navController)
        }

        composable(NavRoutes.CreateOrJoin.route){
            CreateOrJoinUi(navController)
        }

        composable(NavRoutes.Create.route){
            CreateTeamUi(navController)
        }

        composable(NavRoutes.TeamDetail.route){backStack->
            val teamId = backStack.arguments?.getString("teamId") ?: ""

            TeamDetailUi(teamId , navController)
        }

        composable(NavRoutes.Task.route){ backStack ->
            val teamId = backStack.arguments?.getString("teamId") ?: ""
            TaskScreen(teamId , navController)
        }
    }
}