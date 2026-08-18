package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.Task
import com.nisarg.teamsync.domain.repository.TaskRepository
import javax.inject.Inject

class GetTasksByTeamIdUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(teamId : String) : List<Task>{
        return taskRepository.getTasksByTeamId(teamId)
    }
}