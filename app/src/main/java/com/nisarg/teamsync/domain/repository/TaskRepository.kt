package com.nisarg.teamsync.domain.repository

import com.nisarg.teamsync.domain.model.Task

interface TaskRepository {
    suspend fun updateTask(task : Task) : Task
    suspend fun createTask(task : Task) : Task
    suspend fun getTasksByTeamId(teamId : String) : List<Task>
}
