package com.nisarg.teamsync.domain.repository

import com.nisarg.teamsync.domain.model.Team

interface JoinTeamRepository {
    suspend fun joinTeam(inviteCode : String) : Team
}