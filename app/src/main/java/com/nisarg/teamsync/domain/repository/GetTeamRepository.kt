package com.nisarg.teamsync.domain.repository

import com.nisarg.teamsync.domain.model.Team

interface GetTeamRepository {
    suspend fun getTeam() : List<Team>
}