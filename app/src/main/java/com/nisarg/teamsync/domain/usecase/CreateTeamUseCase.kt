package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.Team
import com.nisarg.teamsync.domain.repository.CreateTeamRepository
import javax.inject.Inject

class CreateTeamUseCase @Inject constructor(private val createTeamRepository: CreateTeamRepository) {
    suspend operator fun invoke(name : String) : Team {
        return createTeamRepository.createTeam(name)
    }
}