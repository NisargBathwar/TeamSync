package com.nisarg.teamsync.presentation.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisarg.teamsync.domain.usecase.GetCurrentUserUseCase
import com.nisarg.teamsync.domain.usecase.LoginUseCase
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
class LoginVm @Inject constructor(private val loginUseCase: LoginUseCase , private val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState : StateFlow<LoginState> = _uiState.asStateFlow()
    private val _events = MutableSharedFlow<LoginEvents>()
    val events = _events.asSharedFlow()

    fun onActions(actions: LoginActions){
        when(actions){
            is LoginActions.Login -> {
               viewModelScope.launch {
                    try {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }

                        val login = loginUseCase.invoke(_uiState.value.email, _uiState.value.password)

                        _events.emit(LoginEvents.LoginToHome)

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = null
                            )
                        }

                    } catch (e: Exception) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = e.message
                            )
                        }
                    }
               }
            }
            is LoginActions.EmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = actions.email
                    )
                }
            }
            is LoginActions.PasswordChanged -> {
                _uiState.update {
                    it.copy(
                        password = actions.password
                    )
                }
            }

            is LoginActions.Register -> {
                viewModelScope.launch{ _events.emit(LoginEvents.LogintToRegister) }
            }
        }
    }
}