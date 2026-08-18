package com.nisarg.teamsync.domain.repository

import com.nisarg.teamsync.domain.model.TeamDetail

interface GetTeamByIdRepository {
    suspend fun getTeamById(teamId : String) : TeamDetail
}