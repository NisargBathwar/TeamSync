package com.nisarg.teamsync.presentation.createscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisarg.teamsync.domain.usecase.CreateTeamUseCase
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
class CreateTeamVm @Inject constructor(private val createTeamUseCase: CreateTeamUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateState())
    val uiState : StateFlow<CreateState>  = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<CreateTeamEvents>()
    val events = _events.asSharedFlow()

    fun onActions(actions: CreateTeamActions){
        when(actions){
            is CreateTeamActions.CreateTeam -> {
               viewModelScope.launch {
                   _uiState.update {
                       it.copy(
                           isLoading = true,
                           error = null
                       )
                   }
                  try {
                       createTeamUseCase.invoke(actions.name)

                       _events.emit(CreateTeamEvents.CreateToHome)

                       _uiState.update {
                           it.copy(
                               isLoading = false,
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
            is CreateTeamActions.ChangeTeamName -> {
                _uiState.update {
                    it.copy(
                        teamName = actions.name
                    )
                }
            }
        }
    }
}