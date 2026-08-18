package com.nisarg.teamsync.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nisarg.teamsync.data.mapper.toDomain
import com.nisarg.teamsync.data.model.TeamDto
import com.nisarg.teamsync.data.model.UserDto
import com.nisarg.teamsync.domain.model.Team
import com.nisarg.teamsync.domain.repository.GetTeamRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetTeamRepoImpl @Inject constructor(private val firebaseAuth: FirebaseAuth , private val firebaseFirestore: FirebaseFirestore) : GetTeamRepository {
    override suspend fun getTeam(): List<Team> {

        val currentUser = firebaseAuth.currentUser
        val userDto = firebaseFirestore.collection("users")
            .document(currentUser?.uid ?: "")
            .get()
            .await()
            .toObject(UserDto::class.java)

        val teamIds = userDto?.teamIds ?: emptyList()

        if (teamIds.isEmpty()) {
            return emptyList()
        }

        return teamIds.mapNotNull { teamId ->
            firebaseFirestore.collection("teams")
                .document(teamId)
                .get()
                .await()
                .toObject(TeamDto::class.java)
                ?.toDomain()
        }
    }
}