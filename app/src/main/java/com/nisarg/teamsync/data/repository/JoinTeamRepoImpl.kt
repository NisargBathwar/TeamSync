package com.nisarg.teamsync.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.nisarg.teamsync.data.mapper.toDomain
import com.nisarg.teamsync.data.model.TeamDto
import com.nisarg.teamsync.domain.model.Team
import com.nisarg.teamsync.domain.repository.JoinTeamRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class JoinTeamRepoImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore , private val firebaseAuth: FirebaseAuth) : JoinTeamRepository {
    override suspend fun joinTeam(inviteCode: String): Team {

        val findId = firebaseFirestore.collection("teams")
            .whereEqualTo("inviteCode" , inviteCode)
            .get()
            .await()

        val teamDocument = findId.documents.firstOrNull() ?: throw Exception("Invite code invalid")
        val teamId = teamDocument.id

        val currentUser = firebaseAuth.currentUser ?: throw Exception("User not Logged In")
        val uid = currentUser.uid

        firebaseFirestore.collection("users")
            .document(uid)
            .update("teamIds" , FieldValue.arrayUnion(teamId))
            .await()

        firebaseFirestore.collection("teams")
            .document(teamId)
            .update("members" , FieldValue.arrayUnion(uid))
            .await()

        val teamDto = teamDocument.toObject(TeamDto::class.java) ?: throw Exception("Failed to read team")

        return teamDto.toDomain()

    }
}