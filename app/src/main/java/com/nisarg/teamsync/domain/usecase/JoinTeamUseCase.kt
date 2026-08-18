package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.Team
import com.nisarg.teamsync.domain.repository.JoinTeamRepository
import javax.inject.Inject

class JoinTeamUseCase @Inject constructor(private val joinTeamRepository: JoinTeamRepository) {
    suspend operator fun invoke(inviteCode : String) : Team{
        return joinTeamRepository.joinTeam(inviteCode)
    }
}