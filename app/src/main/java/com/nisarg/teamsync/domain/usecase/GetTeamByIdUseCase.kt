package com.nisarg.teamsync.domain.usecase

import androidx.lifecycle.ViewModel
import com.nisarg.teamsync.domain.model.TeamDetail
import com.nisarg.teamsync.domain.repository.GetTeamByIdRepository
import javax.inject.Inject

class GetTeamByIdUseCase @Inject constructor(private val getTeamByIdRepository: GetTeamByIdRepository) : ViewModel() {
    suspend operator fun invoke(teamId : String) : TeamDetail {
        return getTeamByIdRepository.getTeamById(teamId)
    }
}