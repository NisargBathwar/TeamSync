package com.nisarg.teamsync.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nisarg.teamsync.data.mapper.toDomain
import com.nisarg.teamsync.data.model.UserDto
import com.nisarg.teamsync.domain.model.User
import com.nisarg.teamsync.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(val firebaseAuth: FirebaseAuth , val firebaseFirestore: FirebaseFirestore) : AuthRepository {

    override suspend fun login(email: String, password: String): User {
        val authResult =  firebaseAuth.signInWithEmailAndPassword(email ,password).await()

        val firebaseUser  = authResult.user ?: throw Exception("UserDto is Null")

        val userDto =  firebaseFirestore
            .collection("users")
            .document(firebaseUser.uid)
            .get()
            .await()
            .toObject(UserDto::class.java)
            ?: throw Exception("User Not Found")

        return userDto.toDomain()

    }

    override suspend fun register(email: String, password: String): User {
        val createUser = firebaseAuth.createUserWithEmailAndPassword(email , password).await()

        val user = createUser.user ?: throw Exception("user is null")

        val userDto = UserDto(
            email = user.email ?: "",
            uid = user.uid,
            name = "",
            teamIds = emptyList(),
        )

        firebaseFirestore.collection("users")
            .document(user.uid)
            .set(userDto)
            .await()
        
        return userDto.toDomain()
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }

    override suspend fun getCurrentUser(): User? {
        val firebaseUser = firebaseAuth.currentUser ?: return null

        val userDto = firebaseFirestore.collection("users")
            .document(firebaseUser.uid)
            .get()
            .await()
            .toObject(UserDto::class.java)
            ?: throw Exception("User Not Found")

        return userDto.toDomain()
    }
}