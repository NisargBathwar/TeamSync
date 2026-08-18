package com.nisarg.teamsync.presentation.taskscreen

sealed class TaskActions {
    data class CreateTask(val teamId : String , val title : String , val des : String) : TaskActions()
    data class UpdateTask(val taskId : String , val userId : String) : TaskActions()
}