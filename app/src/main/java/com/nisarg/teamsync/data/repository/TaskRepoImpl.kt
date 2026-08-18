package com.nisarg.teamsync.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.nisarg.teamsync.data.mapper.toDomainTask
import com.nisarg.teamsync.data.model.TaskDto
import com.nisarg.teamsync.domain.model.Task
import com.nisarg.teamsync.domain.repository.TaskRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TaskRepoImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore) : TaskRepository {

    override suspend fun updateTask(task: Task): Task {

        firebaseFirestore.collection("tasks")
            .document(task.id)
            .set(task)
            .await()

        return task
    }

    override suspend fun createTask(task: Task): Task {
        val document = firebaseFirestore.collection("tasks")
            .document()

        val taskWithId = task.copy(
            id =  document.id
        )

        document.set(taskWithId)
            .await()

        return taskWithId
    }

    override suspend fun getTasksByTeamId(teamId: String): List<Task> {

        val snapshot = firebaseFirestore
            .collection("tasks")
            .whereEqualTo("teamId", teamId)
            .get()
            .await()

        return snapshot.documents.mapNotNull { documentSnapshot ->
            documentSnapshot.toObject(TaskDto::class.java) ?.toDomainTask()
        }
    }
}