package com.nisarg.teamsync.presentation.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisarg.teamsync.domain.usecase.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SplashScreenVm @Inject constructor(private val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModel() {

    private val _event = MutableSharedFlow<SplashScreenEvents>()
    val events = _event.asSharedFlow()

    fun checker(){
       viewModelScope.launch {
            delay(1000.milliseconds)

            val users = getCurrentUserUseCase.invoke()

           if (users == null){
               _event.emit(SplashScreenEvents.NavigateToLogin)
           }else if(users.teamIds == null){
               _event.emit(SplashScreenEvents.NavigateToTeamSetup)
           }else{
               _event.emit(SplashScreenEvents.NavigateToHome)
           }
        }
    }
}