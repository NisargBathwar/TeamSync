package com.nisarg.teamsync.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisarg.teamsync.domain.usecase.GetCurrentUserUseCase
import com.nisarg.teamsync.domain.usecase.GetTeamUseCase
import com.nisarg.teamsync.domain.usecase.JoinTeamUseCase
import com.nisarg.teamsync.domain.usecase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor(private val logOutUseCase: LogOutUseCase , private val getCurrentUserUseCase: GetCurrentUserUseCase , private val getTeamUseCase: GetTeamUseCase , private val joinTeamUseCase: JoinTeamUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState : StateFlow<HomeState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<HomeEvents>()
    val events = _events.asSharedFlow()

    init {
        currentUser()
    }

    fun onActions(actions: HomeActions){
        when(actions){
            is HomeActions.LogOut -> {
                viewModelScope.launch {
                    logOutUseCase.invoke()
                    _events.emit(HomeEvents.HomeToLogin)
                }
            }
            is HomeActions.CreateOrJoin -> {
                viewModelScope.launch {
                    _events.emit(HomeEvents.HomeToCreateOrJoin)
                }
            }
           is HomeActions.TeamClicked -> {
               viewModelScope.launch {
                   _events.emit(HomeEvents.HomeToDetail(actions.teamId))
               }
           }
            is HomeActions.Task -> {
                viewModelScope.launch {
                    _events.emit(HomeEvents.HomeToTask(teamId = _uiState.value.teamId))
                }
            }
        }
    }

    fun currentUser(){
        viewModelScope.launch{
            try{
                _uiState.update {
                    it.copy(
                        isLoading = true
                    )
                }

                val user = getCurrentUserUseCase.invoke()
                val team = getTeamUseCase.invoke()

                _uiState.update {
                    it.copy(
                        isLoading = false ,
                        currentUser = user ,
                        team = team ,
                        teamId = user?.teamIds?.firstOrNull() ?: "",
                        error =  null
                    )
                }


            }catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false ,
                        error = e.message
                    )
                }
            }
        }
    }
}