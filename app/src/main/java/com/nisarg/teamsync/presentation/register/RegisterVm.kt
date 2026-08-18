package com.nisarg.teamsync.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisarg.teamsync.domain.usecase.RegisterUseCase
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
class RegisterVm @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState : StateFlow<RegisterState> = _uiState.asStateFlow()

    private val _evets = MutableSharedFlow<RegisterEvents>()
    val events = _evets.asSharedFlow()

    fun onActions(actions: RegisterActions){
        when(actions){
            is RegisterActions.Register -> {
                viewModelScope.launch {
                    try {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }

                        val register = registerUseCase.invoke(_uiState.value.email , _uiState.value.password)

                        _evets.emit(RegisterEvents.RegisterToHome)

                        _uiState.update {
                            it.copy(
                                isLoading = false ,
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
            is RegisterActions.EmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = actions.email
                    )
                }
            }
            is RegisterActions.PasswordChanged -> {
                _uiState.update {
                    it.copy(
                        password = actions.password
                    )
                }
            }
        }
    }
}