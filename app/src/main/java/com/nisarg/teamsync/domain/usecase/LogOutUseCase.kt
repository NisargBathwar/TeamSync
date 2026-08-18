package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(){
        return authRepository.logout()
    }
}