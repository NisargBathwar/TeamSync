package com.nisarg.teamsync.presentation.taskscreen

sealed class TaskEvents {
    data object TaskToHome : TaskEvents()
}