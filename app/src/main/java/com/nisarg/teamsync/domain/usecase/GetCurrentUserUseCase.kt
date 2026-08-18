package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.User
import com.nisarg.teamsync.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() : User?{
        return authRepository.getCurrentUser()
    }
}