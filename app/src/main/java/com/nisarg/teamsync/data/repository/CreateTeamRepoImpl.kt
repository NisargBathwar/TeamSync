package com.nisarg.teamsync.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.nisarg.teamsync.data.mapper.toDomain
import com.nisarg.teamsync.data.model.TeamDto
import com.nisarg.teamsync.domain.model.Team
import com.nisarg.teamsync.domain.repository.CreateTeamRepository
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class CreateTeamRepoImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore, private val firebaseAuth: FirebaseAuth) : CreateTeamRepository {
    override suspend fun createTeam(teamName: String): Team {

        val currentUser = firebaseAuth.currentUser ?: throw Exception("User has not logged In")
        val uid = currentUser.uid

        val teamRef = firebaseFirestore.collection("teams").document()
        val teamId = teamRef.id

        val inviteCode = UUID.randomUUID()
            .toString()
            .replace("-" , "")
            .take(6)
            .uppercase()
        
        val teamDto = TeamDto(
            id = teamId,
            name = teamName,
            ownerId = uid,
            inviteCode = inviteCode,
            members = listOf(uid)
        )

        teamRef.set(teamDto).await()

        firebaseFirestore.collection("users")
            .document(uid)
            .update("teamIds" , FieldValue.arrayUnion(teamId))
            .await()

        return teamDto.toDomain()

    }
}