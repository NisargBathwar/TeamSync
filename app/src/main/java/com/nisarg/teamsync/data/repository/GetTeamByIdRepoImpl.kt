package com.nisarg.teamsync.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nisarg.teamsync.data.mapper.toDomain
import com.nisarg.teamsync.data.model.TeamDto
import com.nisarg.teamsync.data.model.UserDto
import com.nisarg.teamsync.domain.model.TeamDetail
import com.nisarg.teamsync.domain.repository.GetTeamByIdRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetTeamByIdRepoImpl @Inject constructor(private val firebaseAuth: FirebaseAuth , private val firebaseFirestore: FirebaseFirestore) : GetTeamByIdRepository {
    override suspend fun getTeamById(teamId: String): TeamDetail {

        val currentUser = firebaseAuth.currentUser ?: throw Exception("User Not Found")

        val userDto = firebaseFirestore.collection("users")
            .document(currentUser.uid)
            .get()
            .await()
            .toObject(UserDto::class.java)
            ?.toDomain()
            ?: throw Exception("User not Found")

        val teams = userDto.teamIds.mapNotNull { team->
            firebaseFirestore.collection("teams")
                .document(team)
                .get()
                .await()
                .toObject(TeamDto::class.java)
                ?.toDomain()
        }



        val team =  teams.find { it.id == teamId } ?: throw Exception("Team Not Found")

        val members = team.members.mapNotNull { uid->
            firebaseFirestore.collection("users")
                .document(uid)
                .get()
                .await()
                .toObject(UserDto::class.java)
                ?.toDomain()
        }

        return TeamDetail(
            team , members
        )
    }
}