package com.nisarg.teamsync.data.mapper

import com.nisarg.teamsync.data.model.TaskDto
import com.nisarg.teamsync.domain.model.Task

fun TaskDto.toDomainTask() : Task {
    return Task(
        id,
        teamId,
        title,
        description,
        userName,
        isCompleted = isCompleted,
        completedBy = completedBy
    )
}