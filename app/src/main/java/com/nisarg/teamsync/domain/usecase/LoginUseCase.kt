package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.User
import com.nisarg.teamsync.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase  @Inject constructor(private val authRepo : AuthRepository) {
    suspend operator fun invoke(email : String , password : String): User {
        return authRepo.login(
            email = email,
            password = password
        )
    }
}