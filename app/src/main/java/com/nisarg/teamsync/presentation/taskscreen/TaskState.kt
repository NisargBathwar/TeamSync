package com.nisarg.teamsync.presentation.taskscreen

import com.nisarg.teamsync.domain.model.Task

data class TaskState (
    val isLoading : Boolean = false,
    val tasks : List<Task> = emptyList(),
    val currentUseId : String? = null ,
    val error : String? = null
)