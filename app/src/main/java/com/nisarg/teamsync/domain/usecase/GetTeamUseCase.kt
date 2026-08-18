package com.nisarg.teamsync.domain.usecase

import com.nisarg.teamsync.domain.model.Team
import com.nisarg.teamsync.domain.repository.GetTeamRepository
import javax.inject.Inject

class GetTeamUseCase @Inject constructor(private val getTeamRepository: GetTeamRepository) {
    suspend operator fun invoke() : List<Team> {
        return getTeamRepository.getTeam()
    }
}