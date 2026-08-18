package com.nisarg.teamsync.presentation.teamdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.nisarg.teamsync.domain.usecase.GetTasksByTeamIdUseCase
import com.nisarg.teamsync.domain.usecase.GetTeamByIdUseCase
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
class TeamVm @Inject constructor(private val getTeamByIdUseCase: GetTeamByIdUseCase , private val getTasksByTeamIdUseCase: GetTasksByTeamIdUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(TeamState())
    val uiState : StateFlow<TeamState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<TeamEvents>()
    val events = _events.asSharedFlow()

    fun onActions(actions: TeamActions){
        when(actions){
            is TeamActions.Back -> {
                viewModelScope.launch {
                    _events.tryEmit(TeamEvents.TeamDetailBack)
                }
            }
            is TeamActions.Team -> {
                viewModelScope.launch {
                    try {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }

                        val team = getTeamByIdUseCase.invoke(actions.teamId)
                        val tasks = getTasksByTeamIdUseCase.invoke(actions.teamId)

                        _uiState.update {
                            it.copy(
                                isLoading = false ,
                                team = team ,
                                tasks = tasks,
                                error = null
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
    }
}