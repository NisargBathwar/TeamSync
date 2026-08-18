package com.nisarg.teamsync.domain.repository

import com.nisarg.teamsync.domain.model.Team

interface CreateTeamRepository {
    suspend fun createTeam(teamName : String) : Team
}