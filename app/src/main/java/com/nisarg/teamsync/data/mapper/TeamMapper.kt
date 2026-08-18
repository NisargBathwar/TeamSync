package com.nisarg.teamsync.data.mapper

import com.nisarg.teamsync.data.model.TeamDto
import com.nisarg.teamsync.domain.model.Team


fun Team.toDTO() : Team{
    return Team(
        id = id,
        name = name,
        ownerId = ownerId,
        inviteCode = inviteCode,
        members = members
    )
}


fun TeamDto.toDomain() : Team{
    return Team(
        id = id,
        name = name,
        ownerId = ownerId,
        inviteCode = inviteCode,
        members = members
    )
}