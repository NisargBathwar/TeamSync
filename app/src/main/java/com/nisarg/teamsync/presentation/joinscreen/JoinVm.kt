package com.nisarg.teamsync.presentation.joinscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisarg.teamsync.domain.usecase.JoinTeamUseCase
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
class JoinVm @Inject constructor(private val joinTeamUseCase: JoinTeamUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(JoinState())
    val uiState : StateFlow<JoinState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<JoinEvents>()
    val events = _events.asSharedFlow()


    fun onActions(actions: JoinActions){
        when(actions){
            is JoinActions.InviteCodeChanged -> {
                viewModelScope.launch {
                    _uiState.update {
                        it.copy(
                            inviteCode = actions.code
                        )
                    }
                }
            }
            is JoinActions.JoinTeam -> {
                viewModelScope.launch {
                    try {

                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }

                        joinTeamUseCase.invoke(actions.code)
                        _events.emit(JoinEvents.JoinToHome)

                        _uiState.update {
                            it.copy(
                                isLoading = false,
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