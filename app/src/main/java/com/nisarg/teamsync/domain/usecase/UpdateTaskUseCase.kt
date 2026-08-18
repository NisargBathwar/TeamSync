package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.Task
import com.nisarg.teamsync.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task : Task) : Task{
        return taskRepository.updateTask(task)
    }
}