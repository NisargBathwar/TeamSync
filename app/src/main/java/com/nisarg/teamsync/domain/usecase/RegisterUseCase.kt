package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.User
import com.nisarg.teamsync.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email : String , password : String) : User{
        return authRepository.register(email , password)
    }
}