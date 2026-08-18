package com.nisarg.teamsync.domain.repository

import com.nisarg.teamsync.domain.model.User

interface AuthRepository {
    suspend fun login(email : String , password : String) : User

    suspend fun register(email : String , password : String) : User
    suspend fun logout()
    suspend fun getCurrentUser() : User?
}