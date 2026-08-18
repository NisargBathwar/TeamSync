package com.nisarg.teamsync.presentation.taskscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nisarg.teamsync.domain.model.Task
import com.nisarg.teamsync.domain.usecase.CreateTaskUseCase
import com.nisarg.teamsync.domain.usecase.GetCurrentUserUseCase
import com.nisarg.teamsync.domain.usecase.GetTasksByTeamIdUseCase
import com.nisarg.teamsync.domain.usecase.UpdateTaskUseCase
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
class TaskVm @Inject constructor(private val createTaskUseCase: CreateTaskUseCase , private val getTasksByTeamIdUseCase: GetTasksByTeamIdUseCase , private val updateTaskUseCase: UpdateTaskUseCase , private val getCurrentUserUseCase: GetCurrentUserUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(TaskState())
    val uiState : StateFlow<TaskState>  = _uiState.asStateFlow()
    private val _events = MutableSharedFlow<TaskEvents>()
    val events = _events.asSharedFlow()

    fun loadTasks(teamId : String){
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        isLoading = true
                    )
                }

                val tasks = getTasksByTeamIdUseCase.invoke(teamId)
                val currentUserId = getCurrentUserUseCase.invoke()

                _uiState.update {
                    it.copy(
                        isLoading = false ,
                        tasks = tasks ,
                        currentUseId = currentUserId?.uid ,
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

    fun onActions(actions: TaskActions){
        when(actions){
            is TaskActions.CreateTask -> {
                viewModelScope.launch {
                    try {
                        _uiState.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                        val newTask = Task(
                            teamId = actions.teamId,
                            title = actions.title,
                            description = actions.des
                        )
                        val create = createTaskUseCase.invoke(newTask)

                        _events.emit(TaskEvents.TaskToHome)

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                tasks = it.tasks + create,
                                error = null
                            )
                        }
                    }catch (e : Exception){
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = e.message
                            )
                        }
                    }
                }
            }
            is TaskActions.UpdateTask -> {
                viewModelScope.launch {
                    try {
                        val currentTask = _uiState.value.tasks.find { it.id == actions.taskId } ?: return@launch

                        val updatedCompleteBy = currentTask.completedBy.toMutableList()

                        if(actions.userId in updatedCompleteBy){
                            updatedCompleteBy.remove(actions.userId)
                        }else{
                            updatedCompleteBy.add(actions.userId)
                        }

                        val updatedTask = currentTask.copy(
                            completedBy = updatedCompleteBy
                        )

                        val updated = updateTaskUseCase.invoke(updatedTask)

                        _uiState.update {
                            it.copy(
                                tasks = it.tasks.map { task ->
                                    if (task.id == updated.id) updated else task
                                },
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